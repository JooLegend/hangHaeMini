package com.sparta.hanghaemini.account.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
public class Refreshtoken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String refreshToken;

    private String accountUserid;

    public Refreshtoken(String token, String userid) {
        this.refreshToken = token;
        this.accountUserid = userid;
    }

    public Refreshtoken updateToken(String token) {
        this.refreshToken = token;
        return this;
    }
}
