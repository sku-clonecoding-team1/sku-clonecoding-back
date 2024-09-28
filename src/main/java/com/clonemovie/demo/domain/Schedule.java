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
    @JoinColumn(name = "Movie")
    private Movie movie_id;
    private Long cinema_id;     // 서울 - 강남(0)
    private Long theater;       // 몇 관
    private Date date;

    public Schedule(Movie movie_id, Long cinema_id, Long theater, Date date) {
        this.movie_id = movie_id;
        this.cinema_id = cinema_id;
        this.theater = theater;
        this.date = date;
    }

    public String getCinemaName() {
        return CinemaMapper.getCinemaName(this.cinema_id);
    }
}
