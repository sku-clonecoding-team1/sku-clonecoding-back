package com.clonemovie.demo.controller;

import com.clonemovie.demo.exception.InvalidIdorPassword;
import com.clonemovie.demo.exception.InvalidValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidIdorPassword.class)
    public ResponseEntity<String> handleInvalidIdorPassword(InvalidIdorPassword e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 Id나 Password입니다.");
    }

    @ExceptionHandler(InvalidValueException.class)
    public ResponseEntity<String> handleInvalidValueException(InvalidValueException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("유효하지 않은 값입니다.");
    }
}
