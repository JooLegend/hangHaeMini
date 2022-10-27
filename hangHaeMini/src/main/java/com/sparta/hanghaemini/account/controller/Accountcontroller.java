package com.sparta.hanghaemini.account.controller;

import com.sparta.hanghaemini.account.service.Accountservice;
import com.sparta.hanghaemini.account.dto.AccountReqDto;
import com.sparta.hanghaemini.account.dto.LoginResDto;
import com.sparta.hanghaemini.common.CommonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class Accountcontroller {
    private final Accountservice accountservice;

    @PostMapping("/checkid")
    public ResponseEntity<CommonResponseDto<String>> checkid(@RequestBody Map<String, String> request){
        return accountservice.checkid(request);
    }

    @PostMapping("/checkname")
    public ResponseEntity<CommonResponseDto<String>> checkname(@RequestBody Map<String, String> request){
        return accountservice.checkname(request);
    }

    @PostMapping("/signup")
    public ResponseEntity<CommonResponseDto<String>> signup(@RequestBody AccountReqDto request){
        return accountservice.signup(request);
    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponseDto<String>> login(@RequestBody LoginResDto request, HttpServletResponse response){
        return accountservice.login(request, response);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<CommonResponseDto<String>> logout(@RequestHeader Map<String, String> data){
        return accountservice.logout(data.get("access_token"));
    }
}
