package com.sparta.hanghaemini.comment.dto;


import com.sparta.hanghaemini.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {

    private Long commentid;

    private  String nickname;

    private String comment;

    public CommentResponseDto(Comment comment){
        this.commentid = comment.getCommentId();
        this.nickname = comment.getAccount().getNickname();
        this.comment = comment.getComment();
    }
}
