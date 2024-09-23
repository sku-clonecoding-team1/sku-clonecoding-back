package com.clonemovie.demo.DTO;

import com.clonemovie.demo.domain.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoviePageDTO {
    private boolean adult;  // 성인 여부
    private String backdropPath;  // 이미지 경로
    private List<String> genreIds;  // 영화 장르 id 배열
    private Long movieId;  // 영화 고유 번호
    private String originalLanguage;  // 원어
    private String originalTitle;  // 원어 영화 이름
    private String overview;  // 영화 요약
    private double popularity;  // 유명한 정도
    private String posterPath;  // 포스터 이미지 경로
    private String releaseDate;  // 개봉 날짜
    private String title;  // 영화 이름
    private boolean video;  // 비디오 여부
    private double voteAverage;  // 투표율 (평균 점수)
    private Long voteCount;  // 받은 표 수

    public MoviePageDTO(Movie movie) {
        this.adult =movie.isAdult();
        this.backdropPath = movie.getBackdropPath();
        this.genreIds = movie.getGenreIds();
        this.movieId = movie.getMovieId();
        this.originalLanguage = movie.getOriginalLanguage();
        this.originalTitle = movie.getOriginalTitle();
        this.overview = movie.getOverview();
        this.popularity = movie.getPopularity();
        this.posterPath = movie.getPosterPath();
        this.releaseDate = movie.getReleaseDate();
        this.title = movie.getTitle();
        this.video = movie.isVideo();
        this.voteAverage = movie.getVoteAverage();
        this.voteCount = movie.getVoteCount();
    }
}
