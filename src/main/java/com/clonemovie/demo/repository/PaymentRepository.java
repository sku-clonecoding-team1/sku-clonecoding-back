package com.clonemovie.demo.repository;


import com.clonemovie.demo.domain.PaymentLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentLog, Long> {
}
