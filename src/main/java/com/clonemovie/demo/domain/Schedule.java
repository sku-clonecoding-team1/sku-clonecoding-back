package com.clonemovie.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Schedule {
    @Id @GeneratedValue
    private Long id;
    private Long movie_id;
    private Long cinema_id;
    private Long theater;
    private LocalDate time;
    //  좌석 리스트
}
