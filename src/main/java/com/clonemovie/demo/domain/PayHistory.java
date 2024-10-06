package com.clonemovie.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
public class PayHistory {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule scheduleId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member memberId;
    private Long price;
    private Long row;           // 좌석 열
    private Long column;        // 좌석 행
    private LocalDate time;     // 결제 일정
}
