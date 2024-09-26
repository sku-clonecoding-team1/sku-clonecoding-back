package com.clonemovie.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PaymentLog {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")  // 외래 키(FK)로 사용할 컬럼 생성
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticketId")
    private Schedule schedule;

    private Long seatRow;
    private Long seatCol;
    private Long price;
    private LocalDateTime completedAt;  // 완료 시각



    // 요청이 처음 들어온 시간을 설정
    @PrePersist
    protected void onCreate() {
        this.completedAt = LocalDateTime.now();
    }


    public PaymentLog(Member member, Schedule schedule, Long seatRow, Long seatCol, Long price) {
        this.member = member;
        this.schedule = schedule;
        this.price = price;
        this.seatRow = seatRow;
        this.seatCol = seatCol;
    }

    public PaymentLog( Schedule schedule,  Long seatRow, Long seatCol, Long price) {
        this.member = null;
        this.schedule = schedule;
        this.price = price;
        this.seatRow = seatRow;
        this.seatCol = seatCol;
    }


}