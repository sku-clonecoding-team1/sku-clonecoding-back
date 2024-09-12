package com.clonemovie.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Movie {

    @Id @GeneratedValue
    private Long id;

    @Lob
    private String movieHash;

    public Movie(String movieHash) {
        this.movieHash = movieHash;
    }

}
