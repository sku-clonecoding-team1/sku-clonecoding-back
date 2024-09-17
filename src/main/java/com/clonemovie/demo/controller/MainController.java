package com.clonemovie.demo.controller;

import com.clonemovie.demo.DTO.MovieDTO;
import com.clonemovie.demo.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final MovieService movieService;

    @GetMapping("/")
    public ResponseEntity<List<MovieDTO.MovieResponse>> index() {
        try {
            // 성공 시 200 상태 코드와 함께 응답 반환
           return ResponseEntity.ok(movieService.getNowPlayingMovies());
        } catch (Exception e) {
            // 예외 발생 시 MovieResponse의 빈 객체 반환
            List<MovieDTO.MovieResponse> emptyResponse = new ArrayList<>();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(emptyResponse);  // 빈 MovieResponse 반환
        }
    }

}

