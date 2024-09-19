package com.clonemovie.demo.DTO;

import com.clonemovie.demo.domain.Movie;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

public class MovieDetailDTO {
    @Data
    public static class MovieDetailResponse {
        private Long movieId;
        private String title;
        private String originalTitle;
        private String overview;
        private String posterPath;
        private boolean adult;
        private LocalDate releaseDate;
        private double voteAverage;
        private List<GenreDTO> genres;
    }

    @Data
    public static class GenreDTO {
        private String name;
    }
}
