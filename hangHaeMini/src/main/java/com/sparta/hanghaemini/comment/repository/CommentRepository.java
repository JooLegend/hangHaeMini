package com.sparta.hanghaemini.comment.repository;

import com.sparta.hanghaemini.account.entity.Account;
import com.sparta.hanghaemini.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository  extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost_PostId(Long postid);
    Comment findByPost_PostIdAndCommentId(Long postid, Long commentid);

}
