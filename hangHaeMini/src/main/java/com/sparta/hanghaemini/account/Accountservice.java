package com.sparta.hanghaemini.account;

import com.sparta.hanghaemini.account.dto.AccountReqDto;
import com.sparta.hanghaemini.account.dto.LoginResDto;
import com.sparta.hanghaemini.account.entity.Account;
import com.sparta.hanghaemini.account.repository.AccountRepository;
import com.sparta.hanghaemini.account.repository.RefreshtokenRepository;
import com.sparta.hanghaemini.common.CommonResponseDto;
import com.sparta.hanghaemini.jwt.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class Accountservice {
    private final AccountRepository accountRepository;
    private final RefreshtokenRepository refreshtokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private static final String USERID = "userid";
    private static final String NICKNAME = "nickname";

    @Transactional(readOnly = true)
    public void checking(String check, String type){
        if(type.equals(USERID)){
            if(accountRepository.findAccountByUserid(check).isPresent()) throw new RuntimeException("userid 이미 존재");
        } else{
            if(accountRepository.findAccountByNickname(check).isPresent()) throw new RuntimeException("nickname 이미 존재");
        }
    }

    public ResponseEntity<?> checkid(Map<String, String> request) {
        try {
            checking(request.get(USERID), USERID);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(CommonResponseDto.success(null));
        } catch (RuntimeException ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(CommonResponseDto.fail(ex.getMessage()));
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> checkname(Map<String, String> request) {
        try {
            checking(request.get(NICKNAME), NICKNAME);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(CommonResponseDto.success(null));
        } catch (RuntimeException ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(CommonResponseDto.fail(ex.getMessage()));
        }
    }

    @Transactional
    public ResponseEntity<?> signup(AccountReqDto accountReqDto){
        try{
            //중복검사 한번더함
            checking(accountReqDto.getUserid(), USERID);
            checking(accountReqDto.getNickname(), NICKNAME);
            if(!accountReqDto.checkpassword()) throw new RuntimeException("확인 비밀번호가 다릅니다.");
            accountReqDto.setEcodePwd(passwordEncoder.encode(accountReqDto.getPassword()));
            accountRepository.save(new Account(accountReqDto));
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(CommonResponseDto.success(null));
        } catch (RuntimeException ex){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(CommonResponseDto.fail(ex.getMessage()));
        }
    }

    @Transactional
    public ResponseEntity<?> login(LoginResDto loginResDto, HttpServletResponse response){
        try {
            Account account = accountRepository.findAccountByUserid(loginResDto.getUserid()).orElseThrow(
                    () -> new RuntimeException("아이디 조회불가")
            );
            if(!passwordEncoder.matches(loginResDto.getPassword(), account.getPassword()))
                throw new RuntimeException("비밀번호 일치하지 않음");

            String userid = account.getUserid();
            if(!jwtUtil.reporftoken(userid)) response.setHeader(JwtUtil.Access_Token, jwtUtil.createAllToken(userid));
            else{
                response.setHeader(JwtUtil.Access_Token, jwtUtil.createToken(userid, JwtUtil.Access_Token));
                jwtUtil.changerftoken(userid);
            }
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(CommonResponseDto.success(null));
        } catch (RuntimeException ex){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(CommonResponseDto.fail(ex.getMessage()));
        }
    }

    @Transactional
    public ResponseEntity<?> logout(String actk){
        String userid = jwtUtil.getClamsFromToken(actk).getSubject();
        if(jwtUtil.reporftoken(userid))   refreshtokenRepository.deleteByAccountUserid(userid);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CommonResponseDto.success(null));
    }
}