package com.sparta.hanghaemini.post.repository;

import com.sparta.hanghaemini.account.entity.Account;
import com.sparta.hanghaemini.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    Post findPostByPostIdAndAccount(Long postid, Account account);
    Post findPostByPostId(Long postid);
}
