package com.sparta.hanghaemini.security.user;

import com.sparta.hanghaemini.account.entity.Account;
import com.sparta.hanghaemini.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        Account account = accountRepository.findByNickname(nickname).orElseThrow(
                () -> new RuntimeException("해당 사용자가 없습니다.")
        );
        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.setAccount(account);
        return userDetails;
    }
}
