package com.sparta.hanghaemini.post.entity;

import com.sparta.hanghaemini.account.entity.Account;
import com.sparta.hanghaemini.comment.entity.Comment;
import com.sparta.hanghaemini.common.Timestamped;
import com.sparta.hanghaemini.post.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String imgUrl;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "post")
    private List<Comment> comments;

    public Post(PostRequestDto postRequestDto, Account account) {
        this.account = account;
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
        this.imgUrl = postRequestDto.getImgUrl();
    }
}
