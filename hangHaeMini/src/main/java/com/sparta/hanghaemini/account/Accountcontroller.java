package com.sparta.hanghaemini.account;

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
        System.out.println("======================================컨트롤러 지나가는중======================================");
        return accountservice.checkid(request);
    }

    @PostMapping("/checkname")
    public ResponseEntity<CommonResponseDto<String>> checkname(@RequestBody Map<String, String> request){
        System.out.println("======================================컨트롤러 지나가는중======================================");
        return accountservice.checkname(request);
    }

    @PostMapping("/signup")
    public ResponseEntity<CommonResponseDto<String>> signup(@RequestBody AccountReqDto request){
        System.out.println("======================================컨트롤러 지나가는중======================================");
        return accountservice.signup(request);
    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponseDto<String>> login(@RequestBody LoginResDto request, HttpServletResponse response){
        System.out.println("======================================컨트롤러 지나가는중======================================");
        return accountservice.login(request, response);
    }
}
