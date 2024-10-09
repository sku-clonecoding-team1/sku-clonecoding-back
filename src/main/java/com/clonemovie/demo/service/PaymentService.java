package com.clonemovie.demo.service;

import com.clonemovie.demo.DTO.PaymentLogDTO;
import com.clonemovie.demo.domain.Movie;
import com.clonemovie.demo.domain.PaymentLog;
import com.clonemovie.demo.domain.Schedule;
import com.clonemovie.demo.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final MovieScheduleService movieScheduleService;
    private final MemberService memberService;

    public boolean getPaymentLog(PaymentLogDTO request){
        String[] tmp = request.getSeatID().split("-");
        Long scheduleId= (Long) Long.parseLong(tmp[1]);
        Long seatRow = (Long) Long.parseLong(tmp[2]);
        Long seatCol = (Long) Long.parseLong(tmp[3]);

        return paymentRepository.existsByScheduleIdAndSeatRowAndSeatCol(scheduleId, seatRow, seatCol);

    }


    @Transactional
    public PaymentLog savePaymentLog(PaymentLogDTO request) {
        String[] tmp = request.getSeatID().split("-");
        Long scheduleId= (Long) Long.parseLong(tmp[1]);
        Long seatRow = (Long) Long.parseLong(tmp[2]);
        Long seatCol = (Long) Long.parseLong(tmp[3]);

        Schedule schedule = movieScheduleService.findMocieSchedule(scheduleId);

        return paymentRepository.save(new PaymentLog( schedule, seatRow, seatCol, request.getPrice()) );


    }


}
