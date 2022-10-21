package com.sparta.hanghaemini.account.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountReqDto {
    private String userid;
    private String nickname;
    private String password;

    public AccountReqDto(String userid, String nickname, String password){
        this.userid = userid;
        this.nickname = nickname;
        this.password = password;
    }

    public void setEcodePwd(String password){ this.password = password;}
}
