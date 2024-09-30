package com.clonemovie.demo.repository;

import com.clonemovie.demo.DTO.ScheduleResDTO;
import com.clonemovie.demo.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.*;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByCinemaId(Long cinemaId);

    default List<ScheduleResDTO> findAllSchedules() {
        return findAll().stream()
                .map(ScheduleResDTO::new)
                .toList();
    }

}
