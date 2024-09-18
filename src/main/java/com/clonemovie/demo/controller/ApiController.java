package com.clonemovie.demo.controller;

import com.clonemovie.demo.DTO.MovieDetailDTO.*;
import com.clonemovie.demo.DTO.MovieMainDTO.*;
import com.clonemovie.demo.domain.Movie;
import com.clonemovie.demo.repository.MovieRepository;
import com.clonemovie.demo.service.MovieApi;
import com.clonemovie.demo.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final MovieRepository movieRepository;
    private final MovieService movieService;


    @GetMapping("/main")
    public ResponseEntity<List<MovieDetailResponse>> findAllMovies() {
        List<MovieDetailResponse> movieDetailResponses = movieService.getAllMovies();
        return ResponseEntity.ok(movieDetailResponses);
    }


    @Operation(summary = "메인 페이지(영화Id, title, popularity, vote_average, posterPath)", description = "메인 페이지 조회", tags = "MainPage",
                responses = {@ApiResponse(responseCode = "200", description = "db에 있는 영화 정보 조회")})
    @GetMapping("/mainpage")
    public List<MovieMainResponse> findAllMovie() {          // 영화 제목, popularity, vote_average, id
        List<MovieMainResponse> responseMovie = new ArrayList<>();
        for(Movie movie : movieRepository.findAll()) {
            responseMovie.add(new MovieMainResponse(movie));
        }
        return responseMovie;
    }
}
