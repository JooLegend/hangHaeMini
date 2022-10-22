package com.sparta.hanghaemini.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
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
        String servletPath = request.getServletPath();
        String accessToken = jwtUtil.getHeaderToken(request);

        if(accessToken != null && !servletPath.equals("/account/logout")){
            int state = jwtUtil.actokenValidation(accessToken);
            if(state == 3){
                jwtExceptionHandler(response, "Unsorport Token", HttpStatus.BAD_REQUEST);
                return;
            }
            String userid = jwtUtil.getClamsFromToken(accessToken).getSubject();
            if (state == 2) { //엑세스 토큰이 만료시에
                String refreshtoken = jwtUtil.getrftoken(userid).getRefreshToken();
                if(jwtUtil.actokenValidation(refreshtoken) == 1) { //rf토큰이 유효하다면
                    jwtUtil.changerftoken(userid); //기존 rf토큰이 있으면 교체
                    response.setHeader(JwtUtil.Access_Token, jwtUtil.createToken(userid, JwtUtil.Access_Token));
                } else{ //rf토큰 만료시
                    jwtExceptionHandler(response, "login please", HttpStatus.BAD_REQUEST);
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