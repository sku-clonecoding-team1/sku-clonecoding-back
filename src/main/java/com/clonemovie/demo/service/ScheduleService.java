package com.clonemovie.demo.service;

import com.clonemovie.demo.DTO.ScheduleDTO;
import com.clonemovie.demo.domain.Schedule;
import com.clonemovie.demo.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public Schedule addSchedule(ScheduleDTO request) {
        Schedule schedule = new Schedule(request.getMovieId(), request.getCinemaId(), request.getTheater(), request.getDate());
        return scheduleRepository.save(schedule);
    }

    public Schedule findById(Long id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    public String getCinemaName(Long cinemaId) {
        return CinemaMapper.getCinemaName(cinemaId);
    }

    public List<Schedule> findSchedulebyCinemaId(Long cinemaId) {
        return scheduleRepository.findByCinemaId(cinemaId);
    }

    @Transactional
    public void deleteSchedule(Long id) {
        Schedule schedule = findById(id);
        scheduleRepository.delete(schedule);
    }
}
