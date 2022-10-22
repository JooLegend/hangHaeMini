package com.sparta.hanghaemini.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor //기본 생성자 생성
@AllArgsConstructor //모든 생성자..?
public class CommonResDto<T>{

    private Boolean success;
    private T data;
    private Error error;

    public static <T> CommonResDto<T> success(T data){
        return new CommonResDto<>(true, data, null);
    }
    public static <T> CommonResDto<T> fail(String code, String msg){
        return new CommonResDto<>(false, null, new Error(code, msg));
    }
}
