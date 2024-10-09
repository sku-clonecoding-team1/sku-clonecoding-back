package com.clonemovie.demo.controller;

import com.clonemovie.demo.DTO.MovieDetailDTO.*;
import com.clonemovie.demo.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final MovieService movieService;

    @Operation(summary = "db에 있는 모든 영화 정보 조회", description = "메인 페이지 조회", tags = "MainPage",
                responses = {@ApiResponse(responseCode = "200", description = "db에 있는 영화 정보 조회")})
    @GetMapping("/main")
    public ResponseEntity<List<MovieDetailResponse>> findAllMovies() {
        List<MovieDetailResponse> movieDetailResponses = movieService.getAllMovies();
        return ResponseEntity.ok(movieDetailResponses);
    }
}
