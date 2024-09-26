package com.clonemovie.demo.DTO;

import com.clonemovie.demo.domain.Schedule;
import lombok.Data;

import java.util.Date;

@Data
public class ScheduleResponseDTO {
    private Long scheduleId;
    private Long cinemaId;
    private String cinemaName;
    private Long movieId;
    private Long theaterId;
    private Date scheduleDate;


    // 생성자
    public ScheduleResponseDTO(Schedule schedule, String cinemaName) {
        this.scheduleId = schedule.getId();
        this.cinemaId = schedule.getCinemaId();
        this.cinemaName = cinemaName;
        this.movieId = schedule.getMovieId();
        this.theaterId = schedule.getTheaterId();
        this.scheduleDate = schedule.getScheduleDate();
    }
}
