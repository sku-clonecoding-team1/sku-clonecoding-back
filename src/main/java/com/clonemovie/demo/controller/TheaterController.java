package com.clonemovie.demo.controller;

import com.clonemovie.demo.DTO.TheaterDTO;
import com.clonemovie.demo.domain.Movie;
import com.clonemovie.demo.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TheaterController {
    private final MovieRepository movieRepository;

    @GetMapping("/main/theater/{id}")
    public ResponseEntity<?> getTheater(@PathVariable Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(new TheaterDTO(movie));
    }
}
