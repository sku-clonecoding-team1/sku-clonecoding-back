package com.clonemovie.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Schedule {
    @Id @GeneratedValue
    private Long id;
    private Long movie_id;
    private Long cinema_id;     // ex) 서울 - 강남(0) 서울 - 서초(1)
    private Long theater;       // ex) 1관 2관
    private LocalDate time;
    //  좌석 리스트
    @ElementCollection
    @CollectionTable(name = "seat")
    private List<Seat> seat = new ArrayList<>();

    public Schedule(Long movie_id, Long cinema_id, Long theater, LocalDate time, List<Seat> seat) {
        this.movie_id = movie_id;
        this.cinema_id = cinema_id;
        this.theater = theater;
        this.time = time;
        this.seat = initializeSeats();
    }

    public List<Seat> initializeSeats() {
        char[] rows = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M'};

        List<Seat> seats = new ArrayList<>();

        int column = 16;

        for (char row : rows) {
            for (int col = 1; col <= column; col++) {
                seats.add(new Seat(0));
            }
        }
        return seats;
    }
}
