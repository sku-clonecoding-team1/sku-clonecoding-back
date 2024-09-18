package com.clonemovie.demo.DTO;


import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDetailDTO {

    private boolean adult;  // 성인 여부
    private String backdropPath;  // 배경 이미지 경로
    private String belongsToCollection;  // 컬렉션 정보 (null일 수 있음)
    private int budget;  // 예산
    private List<GenreDTO> genres;  // 장르 목록
    private String homepage;  // 홈페이지
    private Long id;  // 영화 고유 번호
    private String imdbId;  // IMDb ID
    private List<String> originCountry;  // 제작 국가 코드
    private String originalLanguage;  // 원어
    private String originalTitle;  // 원제목
    private String overview;  // 영화 개요
    private double popularity;  // 유명도
    private String posterPath;  // 포스터 이미지 경로
    private List<ProductionCompanyDTO> productionCompanies;  // 제작사 목록
    private List<ProductionCountryDTO> productionCountries;  // 제작 국가 목록
    private String releaseDate;  // 개봉일
    private int revenue;  // 수익
    private int runtime;  // 영화 상영 시간
    private List<SpokenLanguageDTO> spokenLanguages;  // 언어 목록
    private String status;  // 상태 (Released 등)
    private String tagline;  // 태그라인
    private String title;  // 영화 제목
    private boolean video;  // 비디오 여부
    private double voteAverage;  // 평균 평점
    private int voteCount;  // 투표 수




}
