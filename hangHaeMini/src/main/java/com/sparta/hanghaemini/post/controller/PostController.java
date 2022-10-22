package com.sparta.hanghaemini.post.controller;

import com.sparta.hanghaemini.common.CommonResDto;
import com.sparta.hanghaemini.post.dto.PostRequestDto;
import com.sparta.hanghaemini.post.dto.PostResponseDto;
import com.sparta.hanghaemini.post.service.PostService;
import com.sparta.hanghaemini.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/posts")
    public CommonResDto<PostResponseDto> createPost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.createPost(postRequestDto, userDetails.getAccount());
    }

    @GetMapping("/getPost/{id}")
    public CommonResDto<PostResponseDto> getPost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception {
        return postService.getPost(id,userDetails.getAccount());
    }

    @GetMapping("/getPosts")
    public CommonResDto<?> getPosts() {
        return postService.getPosts();
    }

    @PutMapping("/updatePost/{id}")
    public CommonResDto<PostResponseDto> update(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.update(id, postRequestDto, userDetails.getAccount());
    }

    @DeleteMapping("/deletePost/{id}")
    public CommonResDto<String> delete(@PathVariable Long id,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.delete(id,userDetails.getAccount());
    }
}
