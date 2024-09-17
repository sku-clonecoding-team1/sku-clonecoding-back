package com.clonemovie.demo.service;

import com.clonemovie.demo.domain.Movie;
import com.clonemovie.demo.repository.MovieRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieApi {
    private final MovieRepository movieRepository;
    private final OkHttpClient client = new OkHttpClient();

    @PostConstruct
    public void initialize() {
        try {
            fetchNowPlayingMovies();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public String fetchNowPlayingMovies() throws IOException {
        int totalPages = 3;  // 호출할 페이지 수
        ObjectMapper objectMapper = new ObjectMapper();
        List<Movie> movies = new ArrayList<>();

        for (int page = 1; page <= totalPages; page++) {
            Request request = new Request.Builder()
                    .url("https://api.themoviedb.org/3/movie/now_playing?language=ko-US&page=" + page)
                    .get()
                    .addHeader("accept", "application/json")
                    .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmODViMTFmOGFiZTY3NWFiZDNkZDM2NTZiYmViNDE4YSIsIm5iZiI6MTcyNTUyODE4NC43OTY3NzUsInN1YiI6IjY2ZDk3NWY1YTZiZGUwZDlhZDI3MGFlMCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.yT5wt2wsTBu1BFuOHFS-JB-z60U1LKN9W-evZb7G1OE")
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }

                String responseBody = response.body().string();
                JsonNode results = objectMapper.readTree(responseBody).get("results");

                for (JsonNode node : results) {
                    Long movieId = node.get("id").asLong(); // movie Id 중복 검사

                    if (!movieRepository.existsById(movieId)) {
                        Movie movie = new Movie();
                        movie.setId(node.get("id").asLong());
                        movie.setTitle(node.get("title").asText());
                        movie.setOverview(node.get("overview").asText());
                        movie.setPosterPath(node.get("poster_path").asText());
                        movie.setAdult(node.get("adult").asBoolean());
                        movie.setOriginalTitle(node.get("original_title").asText());
                        movie.setReleaseDate(LocalDate.parse(node.get("release_date").asText()));
                        movie.setVote_average(node.get("vote_average").asDouble());
                        movie.setPopularity(node.get("popularity").asDouble());

                        movies.add(movie);
                    }
                }
            }
        }
        movieRepository.saveAll(movies);
        return "Movies successfully saved";
    }
}