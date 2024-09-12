package com.clonemovie.demo.service;

import com.clonemovie.demo.domain.Movie;
import com.clonemovie.demo.repository.MovieRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieApi {
    private final OkHttpClient client = new OkHttpClient();
    private final MovieRepository movieRepository;

    public String fetchNowPlayingMovies() throws IOException {
        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/movie/now_playing?language=ko-US&page=1")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmODViMTFmOGFiZTY3NWFiZDNkZDM2NTZiYmViNDE4YSIsIm5iZiI6MTcyNTUyODE4NC43OTY3NzUsInN1YiI6IjY2ZDk3NWY1YTZiZGUwZDlhZDI3MGFlMCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.yT5wt2wsTBu1BFuOHFS-JB-z60U1LKN9W-evZb7G1OE")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            String responseBody = response.body().string();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode results = objectMapper.readTree(responseBody).get("results");

            List<Movie> movies = new ArrayList<>();

            for (JsonNode node : results) {
                Movie movie = new Movie();
                movie.setId(node.get("id").asLong());
                movie.setTitle(node.get("title").asText());
                movie.setOverview(node.get("overview").asText());
                movie.setPoster_path(node.get("poster_path").asText());
                movie.setAdult(node.get("adult").asBoolean());
                movie.setOriginal_title(node.get("original_title").asText());
                movie.setRelease_date(LocalDate.parse(node.get("release_date").asText()));
                movie.setVote_average(node.get("vote_average").asDouble());

                List<Long> genreIds = new ArrayList<>();
                for (JsonNode genre : node.get("genre_ids")) {
                    genreIds.add(genre.get("id").asLong());
                }
                movie.setGenre_ids(genreIds);

                movies.add(movie);
            }
            movieRepository.saveAll(movies);
            return "Movies successfully saved";
        }
    }
}