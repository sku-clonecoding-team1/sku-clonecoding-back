package com.clonemovie.demo.service;


import java.util.*;

public class GenreMapper {
    // 장르 ID와 이름을 매핑하는 테이블
    private static final Map<Integer, String> genreMap = new HashMap<>();

    static {
        genreMap.put(28, "액션");
        genreMap.put(12, "어드벤처");
        genreMap.put(16, "애니메이션");
        genreMap.put(35, "코미디");
        genreMap.put(80, "범죄");
        genreMap.put(99, "다큐멘터리");
        genreMap.put(18, "드라마");
        genreMap.put(10751, "가족");
        genreMap.put(14, "판타지");
        genreMap.put(36, "역사");
        genreMap.put(27, "호러");
        genreMap.put(10402, "음악");
        genreMap.put(9648, "미스테리");
        genreMap.put(10749, "로맨스");
        genreMap.put(878, "공상 과학");
        genreMap.put(10770, "TV 영화");
        genreMap.put(53, "스릴러");
        genreMap.put(10752, "전쟁");
        genreMap.put(37, "서부영화");
    }


    // 장르 ID를 한국어 장르명으로 변환하는 메서드
    public String getGenreName(Integer genreId) {
        String genreName = genreMap.get(genreId);
        return genreName != null ? genreName : "알 수 없음"; // 매핑되지 않은 ID에 대한 처리
    }


}