package com.sparta.hanghaemini.jwt.util;

import com.sparta.hanghaemini.account.repository.RefreshtokenRepository;
import com.sparta.hanghaemini.security.user.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final UserDetailsServiceImpl userDetailsService;

    private final RefreshtokenRepository refreshtokenRepository;
}
