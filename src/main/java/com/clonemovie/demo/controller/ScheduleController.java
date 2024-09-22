package com.clonemovie.demo.controller;

import com.clonemovie.demo.domain.Schedule;
import com.clonemovie.demo.repository.ScheduleRepository;
import com.clonemovie.demo.service.ScheduleService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final ScheduleRepository scheduleRepository;

    @PostMapping("/main/schedule")
    public ResponseEntity<Schedule> addSchedule(@RequestBody Schedule schedule) {
        return ResponseEntity.ok(scheduleRepository.save(schedule));
    }

    @GetMapping("/main/schedule")
    public ResponseEntity<?> getAllSchedules() {
        return ResponseEntity.ok(scheduleRepository.findAll());
    }
}
