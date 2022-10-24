package com.sparta.hanghaemini.post.controller;

import com.sparta.hanghaemini.common.CommonResponseDto;
import com.sparta.hanghaemini.post.dto.PostRequestDto;
import com.sparta.hanghaemini.post.dto.PostResponseDto;
import com.sparta.hanghaemini.post.dto.PostsResponseDto;
import com.sparta.hanghaemini.post.service.PostService;
import com.sparta.hanghaemini.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping
@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/post")
    public ResponseEntity<CommonResponseDto<PostResponseDto>> createPost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println("======================================컨트롤러 지나가는중======================================");
        return postService.createPost(postRequestDto, userDetails.getAccount());
    }

    @PatchMapping("/post/{postid}")
    public ResponseEntity<CommonResponseDto<PostResponseDto>> modifyPost(@PathVariable Long postid, @RequestBody Map<String, String> request, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println("======================================컨트롤러 지나가는중======================================");
        return postService.modifyPost(postid, request.get("content"), userDetails.getAccount());
    }

    @DeleteMapping("/post/{postid}")
    public ResponseEntity<CommonResponseDto<String>> delete(@PathVariable Long postid,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println("======================================컨트롤러 지나가는중======================================");
        return postService.delete(postid,userDetails.getAccount());
    }

    @GetMapping("/show/post/{postid}")
    public ResponseEntity<CommonResponseDto<PostResponseDto>> getPost(@PathVariable Long postid){
        System.out.println("======================================컨트롤러 지나가는중======================================");
        return postService.getPost(postid);
    }

    @GetMapping("/show/post")
    public ResponseEntity<CommonResponseDto<List<PostsResponseDto>>> getPosts() {
        System.out.println("======================================컨트롤러 지나가는중======================================");
        return postService.getPosts();
    }

}
