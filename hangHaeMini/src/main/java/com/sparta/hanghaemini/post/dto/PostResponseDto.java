package com.sparta.hanghaemini.post.dto;

import com.sparta.hanghaemini.account.entity.Account;
import com.sparta.hanghaemini.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class PostResponseDto {
    private String nickname;

    private String title;

    private String contents;

    private String imgUrl;

    //그냥 post로 반환하면 account 엔티티가 반환되는데 그걸 postResponseDto를 사용해서 막음
    public PostResponseDto(Post post) {
        this.nickname = post.getNickname();
        this.title = post.getTitle();
        this.contents = post.getContent();
        this.imgUrl = post.getImgUrl();
    }

    public PostResponseDto(PostRequestDto postRequestDto, Account account) {
        this.nickname = postRequestDto.getNickname();
        this.title = postRequestDto.getTitle();
        this.contents = postRequestDto.getContents();
        this.imgUrl = postRequestDto.getImgUrl();
    }
}