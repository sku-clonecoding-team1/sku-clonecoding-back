package com.clonemovie.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class MovieGenres {
    @Id @GeneratedValue
    private Long id;
    private Long movie_id;
    private Long genre_id;
}
