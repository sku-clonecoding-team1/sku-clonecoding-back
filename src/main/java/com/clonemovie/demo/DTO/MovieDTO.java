package com.clonemovie.demo.DTO;

import lombok.Data;

import java.util.List;

public class MovieDTO {

    @Data
    public static class MovieResponse {
        private boolean adult;  // 성인 여부
        private String backdropPath;  // 이미지 경로
        private List<Integer> genreIds;  // 영화 장르 id 배열
        private Long id;  // 영화 고유 번호
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

        public MovieResponse() {}

        public MovieResponse(boolean adult, String backdropPath, List<Integer> genreIds, Long id, String originalLanguage,
                        String originalTitle, String overview, double popularity, String posterPath,
                        String releaseDate, String title, boolean video, double voteAverage, Long voteCount) {
            this.adult = adult;
            this.backdropPath = backdropPath;
            this.genreIds = genreIds;
            this.id = id;
            this.originalLanguage = originalLanguage;
            this.originalTitle = originalTitle;
            this.overview = overview;
            this.popularity = popularity;
            this.posterPath = posterPath;
            this.releaseDate = releaseDate;
            this.title = title;
            this.video = video;
            this.voteAverage = voteAverage;
            this.voteCount = voteCount;
        }

    }

}
