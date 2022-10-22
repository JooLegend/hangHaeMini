package com.sparta.hanghaemini.post.service;

import com.sparta.hanghaemini.account.entity.Account;
import com.sparta.hanghaemini.common.CommonResDto;
import com.sparta.hanghaemini.post.dto.PostRequestDto;
import com.sparta.hanghaemini.post.dto.PostResponseDto;
import com.sparta.hanghaemini.post.entity.Post;
import com.sparta.hanghaemini.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    public CommonResDto<PostResponseDto> createPost(PostRequestDto postRequestDto, Account account) {
        //userdetail정보를 받아와서, 실제 포스트에 저장된 유저아이디 값이랑 비교 하는게 들어잇어요
        Post post = new Post(postRequestDto,account);//넵 보여주세요
        String nickname = post.getNickname();
        if(account.getNickname().equals(nickname)){
            postRepository.save(post);
            PostResponseDto postResponseDto = new PostResponseDto(post);
            return CommonResDto.success(postResponseDto);
        }
        else {
            return CommonResDto.fail("USER_NOT_FOUND","Don't have Access");
        }
    }

    @Transactional(readOnly = true)
    public CommonResDto<PostResponseDto> getPost(Long id, Account account) throws Exception{
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Not found Id")
        );
        String nickname = post.getNickname();
        if(account.getNickname().equals(nickname)){
            PostResponseDto postResponseDto = new PostResponseDto(post);
            return CommonResDto.success(postResponseDto);
        }else {
            return CommonResDto.fail("USER_NOT_FOUND","Don't have Access");
        }
    }

    @Transactional(readOnly = true)
    public CommonResDto<?> getPosts(){
        List<Post> lists = postRepository.findAll();
        return CommonResDto.success(lists);
    }



    @Transactional
    public CommonResDto<PostResponseDto> update(Long id, PostRequestDto postRequestDto, Account account)  {
        //지금 토큰갖고잇는 사람이랑 , 글쓴사람 토큰이랑 똑같은지 판별식 필요
        // 어차피 둘다 유효토큰이면 jwt 필터를 인증하고 수정이 되므로
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Not found Id")
        );
        String nickname = post.getNickname();

        //현재 token의 email과 postRepository에 저장된 email이 같은지 비교
        if (account.getNickname().equals(nickname)){
            post.update(postRequestDto);
            PostResponseDto postResponseDto = new PostResponseDto(post);
            return CommonResDto.success(postResponseDto);
        } else {
            return CommonResDto.fail("USER_NOT_FOUND","Don't have Access");
        }
    }

    @Transactional
    public CommonResDto<String> delete(Long id,Account account){
        Post post = new Post(id,account);
        String nickname = post.getNickname();

        if(account.getNickname().equals(nickname)){
            postRepository.deleteById(id);
            return CommonResDto.success("");
        }else{
            return CommonResDto.fail("USER_NOT_FOUND","Don't have Access");
        }
    }
}
