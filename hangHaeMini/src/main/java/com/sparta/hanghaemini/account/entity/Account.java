package com.sparta.hanghaemini.account.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.hanghaemini.account.dto.AccountReqDto;
import com.sparta.hanghaemini.common.Timestamped;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Account extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String userid;
    @Column(unique = true, nullable = false)
    private String nickname;
    @Column(nullable = false)
    @JsonIgnore
    private String password;

    public Account(AccountReqDto accountReqDto) {
        this.userid = accountReqDto.getUserid();
        this.nickname = accountReqDto.getNickname();
        this.password = accountReqDto.getPassword();
    }
}
