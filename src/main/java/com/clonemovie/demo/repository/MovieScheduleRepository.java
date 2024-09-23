package com.clonemovie.demo.repository;


import com.clonemovie.demo.DTO.ScheduleResponseDTO;
import com.clonemovie.demo.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MovieScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByMovieIdAndCinemaIdAndScheduleDateAfter(Long movieId, Long cinemaId, Date scheduleDate);

}
