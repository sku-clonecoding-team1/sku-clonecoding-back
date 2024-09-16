package com.clonemovie.demo.controller;

import com.clonemovie.demo.service.MovieApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final MovieApi movieApi;

    @GetMapping("/movie-list")
    public ResponseEntity<String> getMovies() {
        try {
            String movieResponse = movieApi.fetchNowPlayingMovies();
            return ResponseEntity.ok(movieResponse);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("오류가 났습니다.");
        }
    }

    @PostMapping("/now-playing")
    public ResponseEntity<String> getNowPlayingMovies() throws IOException {
       String response = movieApi.fetchNowPlayingMovies();
       return ResponseEntity.ok(response);
    }
}
