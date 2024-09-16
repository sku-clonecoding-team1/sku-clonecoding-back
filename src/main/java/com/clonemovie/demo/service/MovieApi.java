package com.clonemovie.demo.service;

import com.clonemovie.demo.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class MovieApi {
    private final MovieRepository movieRepository;
    private final OkHttpClient client = new OkHttpClient();

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

            String hashedResponse = hashWithSHA256(responseBody);
            return hashedResponse;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private String hashWithSHA256(String data) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(data.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    }
}