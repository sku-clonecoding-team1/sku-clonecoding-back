package com.clonemovie.demo.service;

import com.clonemovie.demo.DTO.MovieDetailDTO;
import com.clonemovie.demo.DTO.MovieDetailDTO.*;
import com.clonemovie.demo.domain.Genres;
import com.clonemovie.demo.domain.Movie;
import com.clonemovie.demo.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<MovieDetailResponse> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieDetailResponse> movieDTO = new ArrayList<>();

        for (Movie movie : movies) {
            MovieDetailResponse dto = new MovieDetailResponse();
            dto.setMovieId(movie.getId());
            dto.setTitle(movie.getTitle());
            dto.setOriginalTitle(movie.getOriginalTitle());
            dto.setOverview(movie.getOverview());
            dto.setPosterPath(movie.getPosterPath());
            dto.setAdult(movie.isAdult());
            dto.setReleaseDate(movie.getReleaseDate());
            dto.setVoteAverage(movie.getVote_average());

            List<GenreDTO> genreDTOs = new ArrayList<>();
            for (Genres genre : movie.getGenres()) {
                GenreDTO genreDTO = new GenreDTO();
                genreDTO.setName(genre.getName());
                genreDTOs.add(genreDTO);
            }
            dto.setGenres(genreDTOs);
            movieDTO.add(dto);
        }

        return movieDTO;
    }
}
