package com.clonemovie.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Schedule {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Movie")
    private Movie movie_id;
    private Long cinema_id;
    private Long theater;
    private LocalDate date;
}
