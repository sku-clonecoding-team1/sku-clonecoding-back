package com.clonemovie.demo.service;

import com.clonemovie.demo.DTO.PayHistoryDTO;
import com.clonemovie.demo.domain.Member;
import com.clonemovie.demo.domain.PayHistory;
import com.clonemovie.demo.domain.Schedule;
import com.clonemovie.demo.repository.PayHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PayHistoryService {
    private final PayHistoryRepository payHistoryRepository;
    private final ScheduleService scheduleService;
    private final MemberService memberService;

    @Transactional
    public PayHistory processPayment(PayHistoryDTO request) {
        System.out.println("Payment request received: " + request);

        Schedule schedule = scheduleService.findById(request.getScheduleId());
        Member member = memberService.findById(request.getMemberId());

        PayHistory payHistory = new PayHistory(schedule, member, request.getBuyPrice(), request.getRw(), request.getCol());

        return payHistoryRepository.save(payHistory);
    }

    public List<PayHistory> paymentList(String userId) {
        Member member = memberService.findByUserId(userId);

        return payHistoryRepository.findByMemberId_UserId(member.getUserId());
    }
}
