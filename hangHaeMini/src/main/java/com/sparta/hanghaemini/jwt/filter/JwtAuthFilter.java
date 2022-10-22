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
        String accessToken = jwtUtil.getHeaderToken(request);
        if(accessToken != null){
            int state = jwtUtil.actokenValidation(accessToken);
            String userid = jwtUtil.getClamsFromToken(accessToken).getSubject();
            if (state == 2) { //엑세스 토큰이 만료시에
                accessToken = jwtUtil.createToken(userid);
            } else {
                jwtExceptionHandler(response, "Unsupport Token", HttpStatus.BAD_REQUEST);
                return;
            }
            setAuthentication(userid);
            response.setHeader(JwtUtil.Access_Token, accessToken);
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