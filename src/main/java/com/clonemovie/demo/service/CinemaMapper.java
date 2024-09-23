package com.clonemovie.demo.service;

import java.util.HashMap;
import java.util.Map;

public class CinemaMapper {

    private static final Map<Integer, String> cinemaMap = new HashMap<>();

    static {
        cinemaMap.put(1, "CGV강남");
        cinemaMap.put(2, "CGV강변");
        cinemaMap.put(3, "CGV건대입구");
        cinemaMap.put(4, "CGV구로");
        cinemaMap.put(5, "CGV대학로");
        cinemaMap.put(6, "CGV동대문");
        cinemaMap.put(7, "CGV등촌");
        cinemaMap.put(8, "CGV명동");
        cinemaMap.put(9, "CGV명동역 씨네라이브러리");
        cinemaMap.put(10, "CGV미아");
        cinemaMap.put(11, "CGV불광");
        cinemaMap.put(12, "CGV상봉");
        cinemaMap.put(13, "CGV성신여대입구");
        cinemaMap.put(14, "CGV송파");
        cinemaMap.put(15, "CGV수유");
        cinemaMap.put(16, "CGV신촌아트레온");
        cinemaMap.put(17, "CGV압구정");
        cinemaMap.put(18, "CGV여의도");
        cinemaMap.put(19, "CGV연남");
        cinemaMap.put(20, "CGV영등포");
        cinemaMap.put(21, "CGV왕십리");
        cinemaMap.put(22, "CGV용산아이파크몰");
        cinemaMap.put(23, "CGV중계");
        cinemaMap.put(24, "CGV천호");
        cinemaMap.put(25, "CGV청담씨네시티");
        cinemaMap.put(26, "CGV피카디리1958");
        cinemaMap.put(27, "CGV하계");
        cinemaMap.put(28, "CGV홍대");
        cinemaMap.put(29, "CINE de CHEF 압구정");
        cinemaMap.put(30, "CINE de CHEF 용산아이파크몰");
    }

    public static String getCinemaName(Integer cinemaId) {
        String cinemaName = cinemaMap.get(cinemaId);
        return cinemaName != null ? cinemaName : "알 수 없음"; // 매핑되지 않은 ID에 대한 처리
    }


    public static Map<Integer, String > getCinemaMap(){
        return cinemaMap;
    }
}
