package com.sparta.hanghaemini.comment.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.hanghaemini.account.entity.Account;
import com.sparta.hanghaemini.comment.dto.CommentRequestDto;
import com.sparta.hanghaemini.common.Timestamped;
import lombok.*;
import org.springframework.stereotype.Service;

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
