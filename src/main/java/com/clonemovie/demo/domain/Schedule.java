package com.clonemovie.demo.domain;

import com.clonemovie.demo.service.CinemaMapper;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Schedule {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movieId;
    private Long cinemaId;     // 서울 - 강남(0)
    private Long theater;       // 몇 관
    private Date date;

    public Schedule(Movie movieId, Long cinemaId, Long theater, Date date) {
        this.movieId = movieId;
        this.cinemaId = cinemaId;
        this.theater = theater;
        this.date = date;
    }

    public String getCinemaName() {
        return CinemaMapper.getCinemaName(this.cinemaId);
    }
}
