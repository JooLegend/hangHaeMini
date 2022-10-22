package com.sparta.hanghaemini.post.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private String nickname;
    private String title;
    private String contents;
    private String imgUrl;
}