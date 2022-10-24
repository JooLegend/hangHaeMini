package com.sparta.hanghaemini.comment.Service;


import com.sparta.hanghaemini.account.entity.Account;
import com.sparta.hanghaemini.comment.dto.CommentRequestDto;
import com.sparta.hanghaemini.comment.entity.Comment;
import com.sparta.hanghaemini.comment.repository.CommentRepository;
import com.sparta.hanghaemini.common.CommonResponseDto;
import com.sparta.hanghaemini.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CommentService {


    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


    public ResponseEntity<CommonResponseDto<?>> postComment(CommentRequestDto commentRequestDto, Account account, Long id){


        Comment comment = Comment.builder()
                .comment(commentRequestDto.getComment())
                .post(postRepository.findPostByPostId(id))
                .account(account)
                .build();
        commentRepository.save(comment);
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(new CommonResponseDto<Comment>(true,comment, null));

    }


    public ResponseEntity delete(Account account,Long postid, Long commentid){
        Comment comment = commentRepository.findByPost_PostIdAndCommentId(postid, commentid);
        if(account.getId().equals(comment.getAccount().getId()) || account.getId().equals(comment.getPost().getPostId()) ){
            commentRepository.deleteById(commentid);
            return  ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new CommonResponseDto(true,null, null));
        }else{
            return  ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new CommonResponseDto(false,null, null));
        }

    }



}
