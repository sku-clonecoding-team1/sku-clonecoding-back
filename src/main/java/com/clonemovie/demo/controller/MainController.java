package com.clonemovie.demo.controller;

import com.clonemovie.demo.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final MovieService movieService;

    @GetMapping("/")
    public ResponseEntity<String> index() {
        try {
            // 성공 시 200 상태 코드와 함께 응답 반환
            String result = movieService.getNowPlayingMovies();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            // 예외 발생 시 500 코드와 함께 "FAIL" 반환
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("FAIL");
        }
    }

}

