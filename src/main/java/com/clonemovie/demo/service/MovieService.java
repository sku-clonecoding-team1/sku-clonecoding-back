package com.clonemovie.demo.service;


import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieService {
    // Jackson ObjectMapper를 사용하여 Json을 HashMap으로 변환
    private final OkHttpClient client = new OkHttpClient();

    // 실제 API 키로 교체
    private static final String API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkMGNhNGQyMmVjODk5ZTJhMTZjMTcyY2JkZDlmMDg4MyIsIm5iZiI6MTcyNTUyNzk2Mi41MjU0MDMsInN1YiI6IjY2OGYzNjNjNGE4NTIyMGFhZWU2ZTQzMSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.AndZ865MqPQ2buHuQlIz9WbX6qv4NrYCObJA9-Ntvm8";


    public String getNowPlayingMovies() throws IOException {
        // API 요청
        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/movie/now_playing?language=en-US&page=1")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer " + API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            // 응답 본문을 JSON 문자열로 변환
            String responseBody = response.body().string();

            // SHA-256 해시값 반환
            return sha256Hex(responseBody);
        }
    }

    // SHA-256 해시를 문자열로 변환하는 간단한 메소드
    public static String sha256Hex(String input) {
        return DigestUtils.sha256Hex(input);

    }


}

