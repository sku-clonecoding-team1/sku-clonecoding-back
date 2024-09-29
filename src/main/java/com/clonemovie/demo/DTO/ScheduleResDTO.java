package com.clonemovie.demo.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScheduleResDTO {
    private Long id;
    private Long movieId;
    private Long theater;
    private LocalDateTime date;

}
