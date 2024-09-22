package com.clonemovie.demo.domain;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    private Long id;
    private Long price;
    private Long ticket;
    private String orderUID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")  // 외래 키(FK)로 사용할 컬럼 생성
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @Builder
    public Order(Long price, Long ticket, String orderUID, Member member, Payment payment) {
        this.price = price;
        this.ticket = ticket;
        this.orderUID = orderUID;
        this.member = member;
        this.payment = payment;
    }
}
