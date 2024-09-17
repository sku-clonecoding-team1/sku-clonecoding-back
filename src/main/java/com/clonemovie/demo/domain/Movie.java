package com.clonemovie.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Entity
@Setter
public class Movie {
    @Id
    private Long id;
    private boolean adult;
    private String originalTitle;
    @Column(length = 1000)
    private String overview;
    private double popularity;
    private String posterPath;
    private LocalDate releaseDate;
    private String title;
    private double vote_average;

    @ManyToMany
    @JoinTable(
            name = "movie_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genres> genres = new HashSet<>();
}
