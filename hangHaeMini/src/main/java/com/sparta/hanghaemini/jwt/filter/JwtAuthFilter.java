package com.sparta.hanghaemini.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.hanghaemini.account.entity.Refreshtoken;
import com.sparta.hanghaemini.common.CommonResponseDto;
import com.sparta.hanghaemini.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println("======================================필터를 지나가는중======================================");
        String servletPath = request.getServletPath();
        String accessToken = jwtUtil.getHeaderToken(request);
        System.out.println("토큰 값은: " + accessToken);
        System.out.println("토큰이 다르게: " + request.getHeader("access_token"));
        System.out.println(servletPath);

        if(accessToken != null && !servletPath.equals("/account/logout")){
            int state = jwtUtil.actokenValidation(accessToken);
            System.out.println("현재 토큰의 상태 값은: "+state);
            if(state == 3){
                jwtExceptionHandler(response, "Unsorport Token", HttpStatus.PROXY_AUTHENTICATION_REQUIRED);
                return;
            }
            String userid = jwtUtil.getClamsFromToken(accessToken).getSubject();
            if(state == 2){
                Refreshtoken refreshtoken = jwtUtil.getrftoken(userid);
                if(jwtUtil.actokenValidation(refreshtoken.getRefreshToken()) == 1) {
                    //유효기간 정상시에 토큰 재발급
                    response.setHeader(JwtUtil.Access_Token, jwtUtil.createToken(userid, JwtUtil.Access_Token));
                }else{
                    jwtExceptionHandler(response, "login please", HttpStatus.PROXY_AUTHENTICATION_REQUIRED);
                    return;
                }
            }
            setAuthentication(userid);
        }
        filterChain.doFilter(request, response);
    }

    public void setAuthentication(String userid){
        Authentication authentication = jwtUtil.createAuthentication(userid);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public void jwtExceptionHandler(HttpServletResponse response, String msg, HttpStatus status){
        response.setStatus(status.value());
        response.setContentType("application/json");
        try{
            String json = new ObjectMapper().writeValueAsString(CommonResponseDto.fail(msg));
            response.getWriter().write(json);
        } catch (Exception e){
            log.error(e.getMessage());
        }
    }
}