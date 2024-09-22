package com.clonemovie.demo.service;

import com.clonemovie.demo.DTO.MovieScheduleDTO;
import com.clonemovie.demo.domain.Schedule;
import com.clonemovie.demo.repository.MovieScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional (readOnly = true)
public class MovieScheduleService {
    private final MovieScheduleRepository movieScheduleRepository;

    @Transactional
    public Schedule addMovieSchedule(MovieScheduleDTO request){
        Schedule schedule = new Schedule(request.getCinemaId(), request.getMovieId(), request.getTheaterId(), request.getScheduleDate());
        System.out.println(schedule);
        movieScheduleRepository.save(schedule);
        return schedule;
    }

    @Transactional
    public List<Schedule> getMovieSchedule(MovieScheduleDTO request) {
        return movieScheduleRepository.findByMovieIdAndCinemaIdAndScheduleDateAfter(
                request.getMovieId(),
                request.getCinemaId(),
                request.getScheduleDate()
        );
    }

}
