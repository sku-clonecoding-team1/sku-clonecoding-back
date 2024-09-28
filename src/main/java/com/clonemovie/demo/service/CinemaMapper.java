package com.clonemovie.demo.service;

import java.util.HashMap;
import java.util.Map;

public class CinemaMapper {
    private static final Map<Long, String> cinemaMap = new HashMap<>();

    static {
        cinemaMap.put(1L, "CGV강남");
        cinemaMap.put(2L, "CGV강변");
        cinemaMap.put(3L, "CGV건대입구");
        cinemaMap.put(4L, "CGV구로");
        cinemaMap.put(5L, "CGV대학로");
        cinemaMap.put(6L, "CGV동대문");
        cinemaMap.put(7L, "CGV등촌");
        cinemaMap.put(8L, "CGV명동");
        cinemaMap.put(9L, "CGV명동역 씨네라이브러리");
        cinemaMap.put(10L, "CGV미아");
        cinemaMap.put(11L, "CGV불광");
        cinemaMap.put(12L, "CGV상봉");
        cinemaMap.put(13L, "CGV성신여대입구");
        cinemaMap.put(14L, "CGV송파");
        cinemaMap.put(15L, "CGV수유");
        cinemaMap.put(16L, "CGV신촌아트레온");
        cinemaMap.put(17L, "CGV압구정");
        cinemaMap.put(18L, "CGV여의도");
        cinemaMap.put(19L, "CGV연남");
        cinemaMap.put(20L, "CGV영등포");
        cinemaMap.put(21L, "CGV왕십리");
        cinemaMap.put(22L, "CGV용산아이파크몰");
        cinemaMap.put(23L, "CGV중계");
        cinemaMap.put(24L, "CGV천호");
        cinemaMap.put(25L, "CGV청담씨네시티");
        cinemaMap.put(26L, "CGV피카디리1958");
        cinemaMap.put(27L, "CGV하계");
        cinemaMap.put(28L, "CGV홍대");
        cinemaMap.put(29L, "CINE de CHEF 압구정");
        cinemaMap.put(30L, "CINE de CHEF 용산아이파크몰");
    }

    public static String getCinemaName(Long cinemaId) {
        return cinemaMap.getOrDefault(cinemaId, "알 수 없는 극장");
    }
}
