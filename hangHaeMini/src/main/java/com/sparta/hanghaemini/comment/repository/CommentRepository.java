package com.sparta.hanghaemini.comment.repository;

import com.sparta.hanghaemini.account.entity.Account;
import com.sparta.hanghaemini.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public class CommentRepository  extends JpaRepository<Comment, Long> {


    Comment findByCommentIdAndPost_Id(Long commentid,Long postid);

    Boolean existsByPostIdAndAccount(Long id, Account account);
}
