package com.sparta.hanghaemini.account.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Refreshtoken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String refreshToken;

    @Column(unique = true, nullable = false)
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