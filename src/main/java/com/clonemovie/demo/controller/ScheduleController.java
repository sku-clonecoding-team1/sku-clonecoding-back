package com.clonemovie.demo.controller;

import com.clonemovie.demo.DTO.ScheduleDTO;
import com.clonemovie.demo.domain.Schedule;
import com.clonemovie.demo.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedule")
    public ResponseEntity<Schedule> addSchedule(@RequestBody ScheduleDTO request) {
        Schedule schedule = scheduleService.addSchedule(request);
        return ResponseEntity.ok(schedule);
    }

    @GetMapping("/schedulelist")
    public ResponseEntity<List<Schedule>> getSchedules() {
        List<Schedule> schedules = new ArrayList<>();
        return ResponseEntity.ok(schedules);
    }
}
