package com.clonemovie.demo.DTO;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
@NoArgsConstructor
public class MovieScheduleDTO {

    private Long cinemaId;
    private Long movieId;
    private Long theaterId;
    private Date scheduleDate;



}
