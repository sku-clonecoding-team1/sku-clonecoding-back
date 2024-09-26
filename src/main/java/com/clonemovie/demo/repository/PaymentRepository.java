package com.clonemovie.demo.repository;


import com.clonemovie.demo.domain.PaymentLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentLog, Long> {
    // 좌석 정보가 이미 결제된 적이 있는지 확인하는 메소드
    boolean existsByScheduleIdAndSeatRowAndSeatCol(Long scheduleId, Long seatRow, Long seatCol);
}
