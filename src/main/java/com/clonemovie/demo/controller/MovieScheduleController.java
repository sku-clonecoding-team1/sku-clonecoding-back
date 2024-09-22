package com.clonemovie.demo.controller;


import com.clonemovie.demo.DTO.MovieScheduleDTO;
import com.clonemovie.demo.domain.Schedule;
import com.clonemovie.demo.service.MovieScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieScheduleController {
    private final MovieScheduleService movieScheduleService;

    @PostMapping("/addMovieSchedule")
    public ResponseEntity<Schedule> addMovieSchedule(@RequestBody MovieScheduleDTO request){
        System.out.println(request.getScheduleDate() + "    입니다~~");

        try{
            return ResponseEntity.ok(movieScheduleService.addMovieSchedule(request));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Schedule());
        }
    }


    @PostMapping("/getMovieSchedule")
    public ResponseEntity<List<Schedule>> getMovieSchedule(@RequestBody MovieScheduleDTO request){
        try{
            return ResponseEntity.ok(movieScheduleService.getMovieSchedule(request));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
        }
    }


}
