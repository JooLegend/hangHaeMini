//package com.sparta.hanghaemini.common;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@RestControllerAdvice
//public class RestExceptionHandler {
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<?> exceptionHandler(RuntimeException e){
//        return ResponseEntity
//                .status(HttpStatus.BAD_REQUEST)
//                .body(CommonResponseDto.fail(e.getMessage()));
//    }
//}
