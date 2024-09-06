package com.clonemovie.demo.service;

import lombok.NoArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
@NoArgsConstructor
public class MovieApi {
    private final OkHttpClient client = new OkHttpClient();

    public String fetchNowPlayingMovies() throws IOException {
        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/movie/now_playing?language=en-US&page=1")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmODViMTFmOGFiZTY3NWFiZDNkZDM2NTZiYmViNDE4YSIsIm5iZiI6MTcyNTUyODE4NC43OTY3NzUsInN1YiI6IjY2ZDk3NWY1YTZiZGUwZDlhZDI3MGFlMCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.yT5wt2wsTBu1BFuOHFS-JB-z60U1LKN9W-evZb7G1OE")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }
}