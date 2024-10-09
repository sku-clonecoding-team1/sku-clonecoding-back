package com.clonemovie.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "pay_history")
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
    private Long buyPrice;
    private Long rw;           // 좌석 열
    private Long col;        // 좌석 행
    private LocalDateTime time;     // 결제 일정

    public PayHistory(Schedule scheduleId, Member memberId, Long buyPrice, Long rw, Long col) {
        this.scheduleId = scheduleId;
        this.memberId = memberId;
        this.buyPrice = buyPrice;
        this.rw = rw;
        this.col = col;
        this.time = LocalDateTime.now();
    }
}
