package com.sparta.hanghaemini.account;

import com.sparta.hanghaemini.account.dto.AccountReqDto;
import com.sparta.hanghaemini.account.dto.LoginResDto;
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
    public ResponseEntity<?> checkid(@RequestBody Map<String, String> request){
        return accountservice.checkid(request);
    }

    @PostMapping("/checkname")
    public ResponseEntity<?> checkname(@RequestBody Map<String, String> request){
        return accountservice.checkname(request);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody AccountReqDto request){
        return accountservice.signup(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginResDto request, HttpServletResponse response){
        return accountservice.login(request, response);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader Map<String, String> data){
        return accountservice.logout(data);
    }
}
