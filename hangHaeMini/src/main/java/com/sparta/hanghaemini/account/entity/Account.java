package com.sparta.hanghaemini.account.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.hanghaemini.account.dto.AccountReqDto;
import com.sparta.hanghaemini.common.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
public class Account extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userid;
    private String nickname;
    @JsonIgnore
    private String password;

    public Account(AccountReqDto accountReqDto) {
        this.userid = accountReqDto.getUserid();
        this.nickname = accountReqDto.getNickname();
        this.password = accountReqDto.getPassword();
    }
}
