package com.clonemovie.demo.DTO;


import com.clonemovie.demo.domain.Movie;
import lombok.Data;

public class MovieMainDTO {

    @Data
    public static class MovieMainResponse {
        private Long movieId;
        private String title;
        private double popularity;
        private double voteAverage;
        private String posterPath;

        public MovieMainResponse(Movie movie) {
            this.movieId = movie.getId();
            this.title = movie.getTitle();
            this.popularity = movie.getPopularity();
            this.voteAverage = movie.getVote_average();
            this.posterPath = movie.getPosterPath();
        }
    }
}
