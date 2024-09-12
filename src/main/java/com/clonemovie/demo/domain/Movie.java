package com.clonemovie.demo.domain;

import com.clonemovie.demo.converter.IntegerList;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Setter
public class Movie {
    @Id
    private Long id;

    private String title;
    @Column(length = 1000)
    private String overview;
    private String poster_path;
    private boolean adult;
    @Convert(converter = IntegerList.class)
    private List<Long> genre_ids;
    private String original_title;
    private LocalDate release_date;
    private double vote_average;
    private double popularity;

}
