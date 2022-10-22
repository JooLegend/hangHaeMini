package com.sparta.hanghaemini.jwt.util;

import com.sparta.hanghaemini.account.entity.Refreshtoken;
import com.sparta.hanghaemini.account.repository.RefreshtokenRepository;
import com.sparta.hanghaemini.security.user.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final UserDetailsServiceImpl userDetailsService;
    private final RefreshtokenRepository refreshtokenRepository;

    private static final long Access_Time = 60*1000*2L;
    private static final long Refresh_Time = 60*1000*10L;

    public static final String Access_Token = "Access_Token";

    @Value("${jwt.secret.key}")
    private String secretKey;
    private Key key;
    private final SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;

    @PostConstruct
    public void init(){
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    //header의 토큰 가져오기
    public String getHeaderToken(HttpServletRequest request){
        return request.getHeader(Access_Token);
    }

    public String createAllToken(String userid){
        refreshtokenRepository.save(new Refreshtoken(createToken(userid, "RF"), userid));
        return createToken(userid, Access_Token);
    }

    //토큰 생성기
    public String createToken(String userid, String type){
        Date date = new Date();
        long time = type.equals(JwtUtil.Access_Token) ? Access_Time : Refresh_Time;
        return Jwts.builder()
                .setSubject(userid)
                .setExpiration(new Date(date.getTime()+time))
                .setIssuedAt(date)
                .signWith(key, algorithm)
                .compact();
    }

    // access토큰 검증기능
    public int actokenValidation(String token){
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return 1;
        } catch (ExpiredJwtException ex){
            return 2;
        } catch (Exception ex){
            return 3;
        }
    }

    // re토큰 빼오기
    public Refreshtoken getrftoken(String userid){
        return refreshtokenRepository.findByAccountUserid(userid);
    }

    // re토큰 존재 확인
    public Boolean reporftoken(String userid){
        return refreshtokenRepository.existsByAccountUserid(userid);
    }

    // re토큰 교체하기
    public void changerftoken(String userid){
        Refreshtoken refreshtoken = getrftoken(userid);
        refreshtokenRepository.save(refreshtoken.updateToken(createToken(userid, "RF")));
    }

    // 이것이 사용되면 context에 사용 가능 유저로 담김
    public Authentication createAuthentication(String userid){
        UserDetails userDetails = userDetailsService.loadUserByUsername(userid);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // jwt 암호풀기
    public Claims getClamsFromToken(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException ex) {
            return ex.getClaims();
        } catch (RuntimeException ex) {
            return null;
        }
    }
}
