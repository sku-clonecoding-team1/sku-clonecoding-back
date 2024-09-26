package com.clonemovie.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue
    private Long id;

    private Long cinemaId;
    private Long movieId;
    private Long theaterId;
    private Date scheduleDate;


    public Schedule(Long cinemaId, Long movieId, Date scheduleDate) {
        this.cinemaId = cinemaId;
        this.movieId = movieId;
        this.scheduleDate = scheduleDate;
    }


    public Schedule(Long cinemaId, Long movieId, Long theaterId, Date scheduleDate) {
        this.cinemaId = cinemaId;
        this.movieId = movieId;
        this.theaterId = theaterId;
        this.scheduleDate = scheduleDate;
    }



}
