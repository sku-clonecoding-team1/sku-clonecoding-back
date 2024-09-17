package com.clonemovie.demo.service;


import com.clonemovie.demo.DTO.MovieDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieService {
    // Jackson ObjectMapper를 사용하여 Json을 HashMap으로 변환
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    // 실제 API 키로 교체
    private static final String API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkMGNhNGQyMmVjODk5ZTJhMTZjMTcyY2JkZDlmMDg4MyIsIm5iZiI6MTcyNTUyNzk2Mi41MjU0MDMsInN1YiI6IjY2OGYzNjNjNGE4NTIyMGFhZWU2ZTQzMSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.AndZ865MqPQ2buHuQlIz9WbX6qv4NrYCObJA9-Ntvm8";


    public List<MovieDTO.MovieResponse> getNowPlayingMovies() throws IOException {
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
            String convertHash = sha256Hex(responseBody);
            // JSON 응답을 파싱하여 'results' 배열을 추출
            JsonNode resultsNode = objectMapper.readTree(responseBody).get("results");

            // MovieResponse 객체 리스트를 저장할 리스트 생성
            List<MovieDTO.MovieResponse> movieResponses = new ArrayList<>();

            // results 배열을 순회하면서 각 항목을 MovieResponse 객체로 변환
            for (JsonNode jsonNode : resultsNode) {
                // genre_ids 배열 추출
                JsonNode genreIdsNode = jsonNode.get("genre_ids");

                // genre_ids 배열을 List<Integer>로 변환
                List<Integer> genreIds = new ArrayList<>();
                Iterator<JsonNode> iterator = genreIdsNode.elements();
                while (iterator.hasNext()) {
                    genreIds.add(iterator.next().asInt());
                }

                // MovieResponse 객체 생성
                MovieDTO.MovieResponse movieResponse = new MovieDTO.MovieResponse(
                        jsonNode.get("adult").asBoolean(),
                        jsonNode.get("backdrop_path").asText(),
                        genreIds,
                        jsonNode.get("id").asLong(),
                        jsonNode.get("original_language").asText(),
                        jsonNode.get("original_title").asText(),
                        jsonNode.get("overview").asText(),
                        jsonNode.get("popularity").asDouble(),
                        jsonNode.get("poster_path").asText(),
                        jsonNode.get("release_date").asText(),
                        jsonNode.get("title").asText(),
                        jsonNode.get("video").asBoolean(),
                        jsonNode.get("vote_average").asDouble(),
                        jsonNode.get("vote_count").asLong()
                );

                // 리스트에 추가
                movieResponses.add(movieResponse);
            }

            // 모든 MovieResponse 객체가 담긴 리스트를 반환
            return movieResponses;
        }
    }

    // SHA-256 해시를 문자열로 변환하는 간단한 메소드
    public static String sha256Hex(String input) {
        return DigestUtils.sha256Hex(input);
    }


}

