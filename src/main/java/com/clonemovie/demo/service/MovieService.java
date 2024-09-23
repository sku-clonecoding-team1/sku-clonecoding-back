package com.clonemovie.demo.service;


import com.clonemovie.demo.DTO.*;
import com.clonemovie.demo.configuration.TmdbProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MovieService {
    // Jackson ObjectMapper를 사용하여 Json을 HashMap으로 변환
    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final GenreMapper genreMapper = new GenreMapper();

    @Autowired
    private TmdbProperties tmdbProperties;


    public List<MoviePageDTO> getNowPlayingMovies(int page) throws IOException {

        // URL을 빌더를 사용하여 구성합니다.
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("api.themoviedb.org")
                .addPathSegment("3")
                .addPathSegment("movie")
                .addPathSegment("now_playing")
                .addQueryParameter("language", "ko")
                .addQueryParameter("page", String.valueOf(page))
                .addQueryParameter("api_key", tmdbProperties.getApiKey())
                .build();

        // API 요청
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer " + tmdbProperties.getApiKey())
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
            List<MoviePageDTO> movieResponses = new ArrayList<>();

            // results 배열을 순회하면서 각 항목을 MovieResponse 객체로 변환
            for (JsonNode jsonNode : resultsNode) {
                // genre_ids 배열 추출
                JsonNode genreIdsNode = jsonNode.get("genre_ids");

                // genre_ids 배열을 List<Integer>로 변환
                List<String> genreIds = new ArrayList<>();
                Iterator<JsonNode> iterator = genreIdsNode.elements();
                while (iterator.hasNext()) {
                    genreIds.add(   genreMapper.getGenreName(iterator.next().asInt()) );
                }

                // MovieResponse 객체 생성
                MoviePageDTO movieResponse = new MoviePageDTO(
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



    public MovieDetailDTO getMoviesDetail(Long movieId) throws IOException {

        // URL을 빌더를 사용하여 영화 세부 정보 API를 구성합니다.
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("api.themoviedb.org")
                .addPathSegment("3")
                .addPathSegment("movie")
                .addPathSegment(movieId.toString())
                .addQueryParameter("language", "ko")
                .addQueryParameter("api_key", tmdbProperties.getApiKey())
                .build();

        // API 요청
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer " + tmdbProperties.getApiKey())
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            // 응답 본문을 JSON 문자열로 변환
            String responseBody = response.body().string();
            // JSON 응답을 파싱
            JsonNode rootNode = objectMapper.readTree(responseBody);

            // JSON 응답에서 각 필드를 추출하여 MovieDetailDTO에 매핑
            MovieDetailDTO movieDetail = new MovieDetailDTO(
                    rootNode.get("adult").asBoolean(),
                    rootNode.get("backdrop_path").asText(),
                    rootNode.has("belongs_to_collection") ? rootNode.get("belongs_to_collection").asText() : null,
                    rootNode.get("budget").asInt(),
                    extractGenres(rootNode.get("genres")),
                    rootNode.get("homepage").asText(),
                    rootNode.get("id").asLong(),
                    rootNode.get("imdb_id").asText(),
                    extractOriginCountries(rootNode.get("origin_country")),
                    rootNode.get("original_language").asText(),
                    rootNode.get("original_title").asText(),
                    rootNode.get("overview").asText(),
                    rootNode.get("popularity").asDouble(),
                    rootNode.get("poster_path").asText(),
                    extractProductionCompanies(rootNode.get("production_companies")),
                    extractProductionCountries(rootNode.get("production_countries")),
                    rootNode.get("release_date").asText(),
                    rootNode.get("revenue").asInt(),
                    rootNode.get("runtime").asInt(),
                    extractSpokenLanguages(rootNode.get("spoken_languages")),
                    rootNode.get("status").asText(),
                    rootNode.get("tagline").asText(),
                    rootNode.get("title").asText(),
                    rootNode.get("video").asBoolean(),
                    rootNode.get("vote_average").asDouble(),
                    rootNode.get("vote_count").asInt()
            );
            // MovieDetailDTO 객체 반환
            return movieDetail;
        }
    }

    // JSON에서 장르 정보를 추출하여 List<GenreDTO>로 변환
    private List<GenreDTO> extractGenres(JsonNode genresNode) {
        List<GenreDTO> genres = new ArrayList<>();
        if (genresNode != null && genresNode.isArray()) {
            for (JsonNode genreNode : genresNode) {
                genres.add(new GenreDTO(
                        genreNode.get("id").asInt(),
                        genreNode.get("name").asText()
                ));
            }
        }
        return genres;
    }

    // JSON에서 제작 국가 정보를 추출하여 List<ProductionCountryDTO>로 변환
    private List<ProductionCountryDTO> extractProductionCountries(JsonNode productionCountriesNode) {
        List<ProductionCountryDTO> productionCountries = new ArrayList<>();
        if (productionCountriesNode != null && productionCountriesNode.isArray()) {
            for (JsonNode productionCountryNode : productionCountriesNode) {
                productionCountries.add(new ProductionCountryDTO(
                        productionCountryNode.get("iso_3166_1").asText(),
                        productionCountryNode.get("name").asText()
                ));
            }
        }
        return productionCountries;
    }

    // JSON에서 제작사 정보를 추출하여 List<ProductionCompanyDTO>로 변환
    private List<ProductionCompanyDTO> extractProductionCompanies(JsonNode productionCompaniesNode) {
        List<ProductionCompanyDTO> productionCompanies = new ArrayList<>();
        if (productionCompaniesNode != null && productionCompaniesNode.isArray()) {
            for (JsonNode productionCompanyNode : productionCompaniesNode) {
                productionCompanies.add(new ProductionCompanyDTO(
                        productionCompanyNode.get("id").asInt(),
                        productionCompanyNode.get("logo_path").asText(),
                        productionCompanyNode.get("name").asText(),
                        productionCompanyNode.get("origin_country").asText()
                ));
            }
        }
        return productionCompanies;
    }

    // JSON에서 언어 정보를 추출하여 List<SpokenLanguageDTO>로 변환
    private List<SpokenLanguageDTO> extractSpokenLanguages(JsonNode spokenLanguagesNode) {
        List<SpokenLanguageDTO> spokenLanguages = new ArrayList<>();
        if (spokenLanguagesNode != null && spokenLanguagesNode.isArray()) {
            for (JsonNode spokenLanguageNode : spokenLanguagesNode) {
                spokenLanguages.add(new SpokenLanguageDTO(
                        spokenLanguageNode.get("english_name").asText(),
                        spokenLanguageNode.get("iso_639_1").asText(),
                        spokenLanguageNode.get("name").asText()
                ));
            }
        }
        return spokenLanguages;
    }

    // JSON에서 제작 국가 코드 정보를 추출하여 List<String>로 변환
    private List<String> extractOriginCountries(JsonNode originCountriesNode) {
        List<String> originCountries = new ArrayList<>();
        if (originCountriesNode != null && originCountriesNode.isArray()) {
            for (JsonNode originCountryNode : originCountriesNode) {
                originCountries.add(originCountryNode.asText());
            }
        }
        return originCountries;
    }

}
