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

    @PostMapping("/movies")
    public ResponseEntity<CommonResponseDto<PostResponseDto>> createPost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.createPost(postRequestDto, userDetails.getAccount());
    }

    @PatchMapping("/movies/{postid}")
    public ResponseEntity<CommonResponseDto<PostResponseDto>> modifyPost(@PathVariable Long postid, @RequestBody Map<String, String> request, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.modifyPost(postid, request.get("content"), userDetails.getAccount());
    }

    @DeleteMapping("/movies/{postid}")
    public ResponseEntity<CommonResponseDto<String>> delete(@PathVariable Long postid,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.delete(postid,userDetails.getAccount());
    }

    @GetMapping("/movies/show/{postid}")
    public ResponseEntity<CommonResponseDto<PostResponseDto>> getPost(@PathVariable Long postid){
        return postService.getPost(postid);
    }

    @GetMapping("/movies/show")
    public ResponseEntity<CommonResponseDto<List<PostsResponseDto>>> getPosts() {
        return postService.getPosts();
    }

}
