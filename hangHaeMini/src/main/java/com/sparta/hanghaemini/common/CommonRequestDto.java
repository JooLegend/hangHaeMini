package com.sparta.hanghaemini.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CommonRequestDto <T>{
    private Boolean success;
    private T data;
    private String error;

    public CommonRequestDto(Boolean success, T data, String error){
        this.success = success;
        this.data = data;
        this.error = error;
    }
}
