package com.clonemovie.demo.service;


import java.util.*;

public class GenreMapper {
    // 장르 ID와 이름을 매핑하는 테이블
    private static final Map<Integer, String> genreMap = new HashMap<>();

    static {
        genreMap.put(28, "Action");
        genreMap.put(12, "Adventure");
        genreMap.put(16, "Animation");
        genreMap.put(35, "Comedy");
        genreMap.put(80, "Crime");
        genreMap.put(99, "Documentary");
        genreMap.put(18, "Drama");
        genreMap.put(10751, "Family");
        genreMap.put(14, "Fantasy");
        genreMap.put(36, "History");
        genreMap.put(27, "Horror");
        genreMap.put(10402, "Music");
        genreMap.put(9648, "Mystery");
        genreMap.put(10749, "Romance");
        genreMap.put(878, "Science Fiction");
        genreMap.put(10770, "TV Movie");
        genreMap.put(53, "Thriller");
        genreMap.put(10752, "War");
        genreMap.put(37, "Western");
    }


    // 장르 ID를 한국어 장르명으로 변환하는 메서드
    public String getGenreName(Integer genreId) {
        String genreName = genreMap.get(genreId);
        return genreName != null ? genreName : "알 수 없음"; // 매핑되지 않은 ID에 대한 처리
    }


}