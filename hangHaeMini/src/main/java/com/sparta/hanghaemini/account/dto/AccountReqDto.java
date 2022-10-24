package com.sparta.hanghaemini.account.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountReqDto {
    private String userid;
    private String nickname;
    private String password;
    private String passwordconfirm;

    public void setEcodePwd(String password){ this.password = password;}
    public boolean checkpassword(){
        return this.password.equals(this.passwordconfirm);
    }
}
