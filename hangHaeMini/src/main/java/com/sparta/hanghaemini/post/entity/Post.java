package com.sparta.hanghaemini.post.entity;

import com.sparta.hanghaemini.account.entity.Account;
import com.sparta.hanghaemini.common.Timestamped;
import com.sparta.hanghaemini.post.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String imgUrl;

    public Post(PostRequestDto postRequestDto) {
        this.nickname = postRequestDto.getNickname();
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContents();
        this.imgUrl = postRequestDto.getImgUrl();
    }

    public Post(PostRequestDto postRequestDto, Account account) {
        this.nickname = postRequestDto.getNickname();
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContents();
        this.nickname = account.getNickname();
    }

    public Post(Long id, Account account) {
        this.postId = id;
        this.nickname = account.getNickname();
    }


    public void update(PostRequestDto postRequestDto) {
        this.nickname = postRequestDto.getNickname();
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContents();
    }

}
