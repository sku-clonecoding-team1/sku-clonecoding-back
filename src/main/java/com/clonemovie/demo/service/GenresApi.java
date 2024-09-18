package com.clonemovie.demo.service;

import com.clonemovie.demo.domain.Genres;
import com.clonemovie.demo.repository.GenresRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GenresApi {
    private final GenresRepository genresRepository;
    private final OkHttpClient client = new OkHttpClient();

    @Transactional
    public String fetchAllGenres() throws IOException {
        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/genre/movie/list?language=ko")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmODViMTFmOGFiZTY3NWFiZDNkZDM2NTZiYmViNDE4YSIsIm5iZiI6MTcyNjUwMTUzNC41NjkwMjksInN1YiI6IjY2ZDk3NWY1YTZiZGUwZDlhZDI3MGFlMCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.PRzmSm7Dk_xbygdXj08OJcibL558T0Ug_FVI58fZoxs")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            String responseBody = response.body().string();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode genresNode = objectMapper.readTree(responseBody).get("genres");

            if (genresNode != null && genresNode.isArray()) {
                for (JsonNode genreNode : genresNode) {
                    Long genreId = genreNode.get("id").asLong();
                    String genreName = genreNode.get("name").asText();

                    if (!genresRepository.existsById(genreId)) {        // 중복 체크
                        Genres genre = new Genres();
                        genre.setId(genreId);
                        genre.setName(genreName);
                        genresRepository.save(genre);
                    }
                }
            }
        }
        return "Genres successfully saved";
    }
}
