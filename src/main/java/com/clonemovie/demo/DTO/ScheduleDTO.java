package com.clonemovie.demo.DTO;

import com.clonemovie.demo.domain.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO {
    private Movie movieId;
    private Long cinemaId;
    private Long theater;
    private Date date;
}
