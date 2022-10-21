package com.sparta.hanghaemini.account.repository;

import com.sparta.hanghaemini.account.entity.Refreshtoken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshtokenRepository extends JpaRepository<Refreshtoken, Long> {
    Optional<Refreshtoken> findByAccountUserid(String userid);
}
