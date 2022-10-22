package com.sparta.hanghaemini.account.repository;

import com.sparta.hanghaemini.account.entity.Refreshtoken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshtokenRepository extends JpaRepository<Refreshtoken, Long> {
    Refreshtoken findByAccountUserid(String userid);

    Boolean existsByAccountUserid(String userid);

    void deleteByAccountUserid(String userid);
}
