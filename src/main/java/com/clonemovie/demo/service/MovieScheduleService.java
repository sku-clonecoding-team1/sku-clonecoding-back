package com.clonemovie.demo.service;

import com.clonemovie.demo.DTO.MovieScheduleDTO;
import com.clonemovie.demo.DTO.ScheduleResponseDTO;
import com.clonemovie.demo.domain.Schedule;
import com.clonemovie.demo.repository.MovieScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional (readOnly = true)
public class MovieScheduleService {
    private final MovieScheduleRepository movieScheduleRepository;
    private final CinemaMapper cinemaMapper = new CinemaMapper();

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


    public List<ScheduleResponseDTO> getMovieScheduleAll() {
        List<Schedule> result = movieScheduleRepository.findAll();
        List<ScheduleResponseDTO> response = new ArrayList<>();

        for (Schedule schedule : result) {
            String cinemaName = cinemaMapper.getCinemaName(Math.toIntExact(schedule.getCinemaId()));
            response.add(new ScheduleResponseDTO(schedule, cinemaName)  );
        }

        return response;
    }

}
