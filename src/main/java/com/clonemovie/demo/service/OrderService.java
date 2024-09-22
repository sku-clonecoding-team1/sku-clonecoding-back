package com.clonemovie.demo.service;

import com.clonemovie.demo.domain.Member;
import com.clonemovie.demo.domain.Order;
import com.clonemovie.demo.domain.Payment;
import com.clonemovie.demo.domain.PaymentStatus;
import com.clonemovie.demo.repository.OrderRepository;
import com.clonemovie.demo.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    public Order save(Member member) {
        Payment payment = Payment.builder()
                .price(1000L)
                .status(PaymentStatus.READY)
                .build();

        paymentRepository.save(payment);

        Order order = Order.builder()
                .price(1000L)
                .ticket(1L)
                .orderUID(UUID.randomUUID().toString())
                .member(member)
                .payment(payment)
                .build();

        return orderRepository.save(order);
    }
}
