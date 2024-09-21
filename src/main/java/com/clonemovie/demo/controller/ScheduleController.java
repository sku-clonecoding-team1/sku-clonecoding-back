package com.clonemovie.demo.controller;

import com.clonemovie.demo.repository.ScheduleRepository;
import com.clonemovie.demo.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final ScheduleRepository scheduleRepository;

    public ResponseEntity<?> getAllSchedules() {
        return ResponseEntity.ok(scheduleRepository.findAll());
    }
}
