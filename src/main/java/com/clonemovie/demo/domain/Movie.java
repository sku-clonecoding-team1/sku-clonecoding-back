package com.clonemovie.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Entity
@Setter
public class Movie {
    @Id
    private Long id;
    private boolean adult;
    private String originalTitle;
    private String overview;
    private double popularity;
    private String posterPath;
    private LocalDate releaseDate;
    private String title;
    private double vote_average;
}
