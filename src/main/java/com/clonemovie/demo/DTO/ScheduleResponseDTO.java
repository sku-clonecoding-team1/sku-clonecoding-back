package com.clonemovie.demo.DTO;

import com.clonemovie.demo.domain.Schedule;
import com.clonemovie.demo.domain.Seat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ScheduleResponseDTO {
    private Long cinemaId;
    private String cinemaName;
    private Long movieId;
    private Long theaterId;
    private Date scheduleDate;
    private List<Seat> seats; // 좌석 정보를 담기 위한 DTO

    // 생성자
    public ScheduleResponseDTO(Schedule schedule, String cinemaName) {
        this.cinemaId = schedule.getCinemaId();
        this.cinemaName = cinemaName;
        this.movieId = schedule.getMovieId();
        this.theaterId = schedule.getTheaterId();
        this.scheduleDate = schedule.getScheduleDate();
        this.seats = schedule.getSeats();
    }
}
