package com.sparta.hanghaemini.post.service;

import com.sparta.hanghaemini.account.entity.Account;
import com.sparta.hanghaemini.common.CommonResponseDto;
import com.sparta.hanghaemini.post.dto.PostRequestDto;
import com.sparta.hanghaemini.post.dto.PostResponseDto;
import com.sparta.hanghaemini.post.dto.PostsResponseDto;
import com.sparta.hanghaemini.post.entity.Post;
import com.sparta.hanghaemini.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    public ResponseEntity<?> createPost(PostRequestDto postRequestDto, Account account) {
        //userdetail정보를 받아와서, 실제 포스트에 저장된 유저아이디 값이랑 비교 하는게 들어잇어요
        Post post = new Post(postRequestDto,account);//넵 보여주세요
        PostResponseDto postResponseDto = new PostResponseDto(postRepository.save(post));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CommonResponseDto.success(postResponseDto));
    }

    @Transactional
    public ResponseEntity<?> modifyPost(Long id, String content, Account account)  {
        try {
            Post post = postRepository.findPostByPostIdAndAccount(id, account);
            if (post == null) throw new RuntimeException("해당 post 수정 권한 없음");
            post.setContent(content);
            PostResponseDto postResponseDto = new PostResponseDto(postRepository.save(post));
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(CommonResponseDto.success(postResponseDto));
        } catch (RuntimeException ex){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(CommonResponseDto.fail(ex.getMessage()));
        }
    }

    @Transactional
    public ResponseEntity<?> delete(Long id,Account account) {
        try {
            Post post = postRepository.findPostByPostIdAndAccount(id, account);
            if (post == null) throw new RuntimeException("해당 post 수정 권한 없음");
            postRepository.deleteById(post.getPostId());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(CommonResponseDto.success(null));
        } catch (RuntimeException ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(CommonResponseDto.fail(ex.getMessage()));
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> getPost(Long id){
        Post post = postRepository.findPostByPostId(id);
        PostResponseDto postResponseDto = new PostResponseDto(post);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postResponseDto);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> getPosts(){
        List<Post> posts = postRepository.findAll();
        List<PostsResponseDto> postsResponseDtoList = new LinkedList<>();
        for(Post post:posts){
            postsResponseDtoList.add(new PostsResponseDto(post));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CommonResponseDto.success(postsResponseDtoList));
    }
}
