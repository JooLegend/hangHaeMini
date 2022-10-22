package com.sparta.hanghaemini.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CommonResponseDto <T>{
    private Boolean success;
    private T data;
    private String error;

    public CommonResponseDto(boolean b, T data, String o) {
        this.success = b;
        this.data = data;
        this.error = o;
    }

    public static <T> CommonResponseDto <T> success(T data){
        return new CommonResponseDto<>(true, data, null);
    }

    public static <T> CommonResponseDto <T> fail(String msg){
        return new CommonResponseDto<>(false, null, msg);
    }
}
