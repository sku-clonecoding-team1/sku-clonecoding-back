package com.clonemovie.demo.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ScheduleDTO {
    private Long movie_id;
    private Long cinema_id;
    private Long theater;
    private LocalDate time;
}
