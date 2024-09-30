package com.clonemovie.demo.DTO;

import com.clonemovie.demo.domain.Movie;
import com.clonemovie.demo.domain.Schedule;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ScheduleResDTO {
    private Long id;
    private Long movieId;
    private String movieTitle;
    private String cinemaName;
    private Long theater;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    public ScheduleResDTO(Schedule schedule){
        this.id = schedule.getId();
        this.movieId = schedule.getMovieId().getId();
        this.movieTitle = schedule.getMovieId().getTitle();
        this.cinemaName = schedule.getCinemaName();
        this.theater = schedule.getTheater();
        this.date = schedule.getDate();
    }
}
