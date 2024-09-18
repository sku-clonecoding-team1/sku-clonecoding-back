package com.clonemovie.demo.controller;

import com.clonemovie.demo.DTO.MovieDetailDTO;
import com.clonemovie.demo.DTO.MoviePageDTO;
import com.clonemovie.demo.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    // 추후 getMoviePage{} <---- 로 변경해서 현재 페이지에 맞는 페이지 API로 변경할 예정임
    @GetMapping("/getMoviePage/{page}")
    public ResponseEntity<List<MoviePageDTO>> getMoviePage(@PathVariable int page){
        try {
            // 성공 시 200 상태 코드와 함께 응답 반환
            return ResponseEntity.ok(movieService.getNowPlayingMovies(page));
        } catch (Exception e) {
            // 예외 발생 시 MovieResponse의 빈 객체 반환
            List<MoviePageDTO> emptyResponse = new ArrayList<>();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(emptyResponse);  // 빈 MovieResponse 반환
        }
    }


    @GetMapping("/getMovieDetail/{movieId}")
    public ResponseEntity<MovieDetailDTO> getMoviePage(@PathVariable Long movieId){
        try {
            // 성공 시 200 상태 코드와 함께 응답 반환
            return ResponseEntity.ok(movieService.getMoviesDetail(movieId));
        } catch (Exception e) {
            // 예외 발생 시 MovieResponse의 빈 객체 반환
            MovieDetailDTO emptyResponse = new MovieDetailDTO();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(emptyResponse);  // 빈 MovieResponse 반환
        }
    }


}
