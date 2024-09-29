package com.clonemovie.demo.controller;

import com.clonemovie.demo.DTO.ScheduleDTO;
import com.clonemovie.demo.domain.Schedule;
import com.clonemovie.demo.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @Operation(summary = "db에 영화 방영 날짜 Insert", description = "movieId, cinemaId, theater, date 필요", tags = "schedule",
            responses = {@ApiResponse(responseCode = "200", description = "db에 저장")})
    @PostMapping("/schedule")
    public ResponseEntity<Schedule> addSchedule(@RequestBody ScheduleDTO request) {
        System.out.println("Received ScheduleDTO: " + request.toString());
        Schedule schedule = scheduleService.addSchedule(request);
        return ResponseEntity.ok(schedule);
    }

    @Operation(summary = "Schedule 테이블에 있는 정보 모두 조회", description = "", tags = "schedule",
            responses = {@ApiResponse(responseCode = "200", description = "db에 있는 정보 조회")})
    @GetMapping("/schedulelist")
    public ResponseEntity<List<Schedule>> getSchedules() {
        List<Schedule> schedules = scheduleService.findAll();
        return ResponseEntity.ok(schedules);
    }
}
