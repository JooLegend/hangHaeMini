package com.sparta.hanghaemini.comment.controller;

import com.sparta.hanghaemini.comment.Service.CommentService;
import com.sparta.hanghaemini.comment.dto.CommentRequestDto;
import com.sparta.hanghaemini.comment.dto.CommentResponseDto;
import com.sparta.hanghaemini.comment.entity.Comment;
import com.sparta.hanghaemini.common.CommonResponseDto;
import com.sparta.hanghaemini.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping
@RestController
@RequiredArgsConstructor
class CommentController {
    private final CommentService commentService;


    @GetMapping("/comments/{postid}")
    public ResponseEntity<CommonResponseDto<List<CommentResponseDto>>> showcomments(@PathVariable Long postid){
        System.out.println("=================커멘트 보내주기=================");
        return commentService.showComments(postid);
    }

    @PostMapping("/comments")
    public ResponseEntity<CommonResponseDto<CommentResponseDto>> addcomments(@RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        System.out.println("=================커멘트 달기=================");
       return commentService.postComment(commentRequestDto,userDetails.getAccount());
    }


    @DeleteMapping("/comments/{postid}/{commentid}")
    public ResponseEntity<CommonResponseDto<String>> delete(@AuthenticationPrincipal UserDetailsImpl userDetails,@PathVariable Long postid,@PathVariable Long commentid){
        System.out.println("=================커멘트 삭제=================");
        return commentService.delete(userDetails.getAccount(),postid,commentid);
    }
}