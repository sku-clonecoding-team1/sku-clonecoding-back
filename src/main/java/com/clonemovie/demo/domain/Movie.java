package com.clonemovie.demo.domain;

import com.clonemovie.demo.DTO.MoviePageDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Movie {

    @Id
    private Long movieId;  // 영화 API로부터 받아온 영화 고유 번호
    private boolean adult;  // 성인 여부
    private String backdropPath;  // 이미지 경로

    @ElementCollection
    private List<String> genreIds;  // 영화 장르 id 배열

    private String originalLanguage;  // 원어
    private String originalTitle;  // 원어 영화 이름
    @Column(length = 2000) // 예시로 최대 2000자로 설정
    private String overview;  // 영화 요약
    private double popularity;  // 유명한 정도
    private String posterPath;  // 포스터 이미지 경로
    private String releaseDate;  // 개봉 날짜
    private String title;  // 영화 이름
    private boolean video;  // 비디오 여부
    private double voteAverage;  // 투표율 (평균 점수)
    private Long voteCount;  // 받은 표 수

    public Movie (MoviePageDTO moviePageDTO) {
        this.movieId = moviePageDTO.getMovieId();
        this.adult = moviePageDTO.isAdult();
        this.backdropPath = moviePageDTO.getBackdropPath();
        this.genreIds = moviePageDTO.getGenreIds();
        this.originalLanguage = moviePageDTO.getOriginalLanguage();
        this.originalTitle = moviePageDTO.getOriginalTitle();
        this.overview = moviePageDTO.getOverview();
        this.popularity = moviePageDTO.getPopularity();
        this.posterPath = moviePageDTO.getPosterPath();
        this.releaseDate = moviePageDTO.getReleaseDate();
        this.title = moviePageDTO.getTitle();
        this.video = moviePageDTO.isVideo();
        this.voteAverage = moviePageDTO.getVoteAverage();
        this.voteCount = moviePageDTO.getVoteCount();
    }

}
