package com.sparta.hanghaemini.post.dto;

import com.sparta.hanghaemini.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Clob;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private Long postid;
    private String nickname;
    private String title;
    private String content;
    private String imgUrl;
    private List<String> comments;

    //그냥 post로 반환하면 account 엔티티가 반환되는데 그걸 postResponseDto를 사용해서 막음
    public PostResponseDto(Post post) {
        this.postid = post.getPostId();
        this.nickname = post.getAccount().getNickname();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.imgUrl = post.getImgUrl();
        this.comments = null;
    }
}