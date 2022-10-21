package com.sparta.hanghaemini.account.repository;

import com.sparta.hanghaemini.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByNickname(String nickname);
}
