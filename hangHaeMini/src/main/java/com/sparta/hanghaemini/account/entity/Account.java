package com.sparta.hanghaemini.account.entity;

import com.sparta.hanghaemini.account.dto.AccountReqDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userid;
    private String nickname;
    private String password;

    public Account(AccountReqDto accountReqDto) {
        this.userid = accountReqDto.getUserid();
        this.nickname = accountReqDto.getNickname();
        this.password = accountReqDto.getPassword();
    }
}
