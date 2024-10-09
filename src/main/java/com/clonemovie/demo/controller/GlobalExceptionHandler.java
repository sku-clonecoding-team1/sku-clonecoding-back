package com.clonemovie.demo.controller;

import com.clonemovie.demo.Exception.InvalidMember;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidMember.class)
    public ResponseEntity<String> handleInvalidMember(InvalidMember e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("회원정보가 없습니다.");
    }
}
