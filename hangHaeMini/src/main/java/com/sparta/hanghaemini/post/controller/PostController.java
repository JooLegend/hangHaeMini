package com.sparta.hanghaemini.post.controller;

import com.sparta.hanghaemini.account.entity.Account;
import com.sparta.hanghaemini.post.dto.PostRequestDto;
import com.sparta.hanghaemini.post.service.PostService;
import com.sparta.hanghaemini.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping
@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/post")
    public ResponseEntity<?> createPost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.createPost(postRequestDto, userDetails.getAccount());
    }

    @PatchMapping("/post/{postid}")
    public ResponseEntity<?> modifyPost(@PathVariable Long postid, @RequestBody Map<String, String> request, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.modifyPost(postid, request.get("content"), userDetails.getAccount());
    }

    @DeleteMapping("/post/{postid}")
    public ResponseEntity<?> delete(@PathVariable Long postid,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.delete(postid,userDetails.getAccount());
    }

    @GetMapping("/show/post/{postid}")
    public ResponseEntity<?> getPost(@PathVariable Long postid){
        return postService.getPost(postid);
    }

    @GetMapping("/show/post")
    public ResponseEntity<?> getPosts() {
        return postService.getPosts();
    }

}
