package com.sparta.hanghaemini.comment.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.hanghaemini.account.entity.Account;

import com.sparta.hanghaemini.common.Timestamped;
import com.sparta.hanghaemini.post.entity.Post;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment extends Timestamped {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String comment;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="post_id")
    private Post post;


    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;


}
