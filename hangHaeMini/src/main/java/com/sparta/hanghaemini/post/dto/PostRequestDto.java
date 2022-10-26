package com.sparta.hanghaemini.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Clob;

@Getter
@NoArgsConstructor
public class PostRequestDto {
    private String title;
    private String content;
    private Clob imgUrl;
}