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

    private String seatID;
    private Long price;
    private int paymentStatus;  // 0: 결제 대기, 1: 결제 완료, -1: 결제 실패
    private LocalDateTime requestedAt;  // 요청 시각
    private LocalDateTime completedAt;  // 완료 시각

    // 요청이 처음 들어온 시간을 설정
    @PrePersist
    protected void onCreate() {
        this.requestedAt = LocalDateTime.now();
    }

    // 결제가 완료될 때 완료 시각을 설정
    @PreUpdate
    protected void onUpdate() {
        if (this.paymentStatus == 1 || this.paymentStatus == -1) {
            this.completedAt = LocalDateTime.now();
        }
    }


    public PaymentLog(Member member, Schedule schedule, String seatID, Long price) {
        this.member = member;
        this.schedule = schedule;
        this.seatID = seatID;
        this.price = price;
        this.paymentStatus = 0;
    }
    public PaymentLog( Schedule schedule, String seatID, Long price) {
        this.member = null;
        this.schedule = schedule;
        this.seatID = seatID;
        this.price = price;
        this.paymentStatus = 0;
    }


}