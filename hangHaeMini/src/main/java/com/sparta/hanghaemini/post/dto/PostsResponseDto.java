package com.sparta.hanghaemini.post.dto;

import com.sparta.hanghaemini.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsResponseDto {
    private Long postid;
    private String title;
    private String content;
    private String imgUrl;

    //그냥 post로 반환하면 account 엔티티가 반환되는데 그걸 postResponseDto를 사용해서 막음
    public PostsResponseDto(Post post) {
        this.postid = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.imgUrl = post.getImgUrl();
    }
}