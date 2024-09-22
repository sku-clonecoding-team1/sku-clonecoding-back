package com.clonemovie.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue
    private Long id;

    private Long cinemaId;
    private Long movieId;
    private Long theaterId;
    private Date scheduleDate;

    // 좌석 리스트 (예: A열)
    @ElementCollection // <-- 기본 데이터 타입 Integer형임을 나타냄
    @CollectionTable(name = "schedule_seats", joinColumns = @JoinColumn(name = "schedule_id")) // <-- schedule_seats라는 table을 만들어서 따로 관리
    private List<Seat> seats;

    public Schedule(Long cinemaId, Long movieId, Date scheduleDate) {
        this.cinemaId = cinemaId;
        this.movieId = movieId;
        this.scheduleDate = scheduleDate;
    }


    public Schedule(Long cinemaId, Long movieId, Long theaterId, Date scheduleDate) {
        this.cinemaId = cinemaId;
        this.movieId = movieId;
        this.theaterId = theaterId;
        this.scheduleDate = scheduleDate;
        this.seats = initializeSeats();
    }

    private List<Seat> initializeSeats() {
        List<Seat> seatList = new ArrayList<>();
        String[] rows = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M"};
        int columns = 16;

        for (String row : rows) {
            for (int col = 1; col <= columns; col++) {
                Seat seat = new Seat();
                seat.setSeatStatus(0);
                seatList.add(seat);
            }
        }
        return seatList;
    }

}
