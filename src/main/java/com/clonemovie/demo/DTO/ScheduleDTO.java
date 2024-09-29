package com.clonemovie.demo.DTO;

import com.clonemovie.demo.domain.Movie;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO {
    private Long movieId;
    private Long cinemaId;
    private Long theater;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
}
