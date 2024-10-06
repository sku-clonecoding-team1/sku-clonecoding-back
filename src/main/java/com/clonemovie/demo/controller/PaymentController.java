package com.clonemovie.demo.controller;

import com.clonemovie.demo.DemoApplication;
import com.clonemovie.demo.domain.Member;
import com.clonemovie.demo.domain.PayHistory;
import com.clonemovie.demo.domain.Schedule;
import com.clonemovie.demo.repository.MemberRepository;
import com.clonemovie.demo.repository.PayHistoryRepository;
import com.clonemovie.demo.repository.ScheduleRepository;
import com.clonemovie.demo.service.MemberService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/v1")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {
    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;
    private final DemoApplication demoApplication;
    private IamportClient iamportClient;
    private PayHistoryRepository payHistoryRepository;
    private MemberService memberService;

    @Value("${spring.iamport.API_KEY}")
    private String apiKey;

    @Value("${spring.iamport.API_SECRETKEY}")
    private String apiSecretKey;

    @PostConstruct
    public void init() {
        this.iamportClient = new IamportClient(apiKey, apiSecretKey);
    }

    @PostMapping("/order/payment/{imp_uid}")        // 결제 요청
    public ResponseEntity<?> validatePayment(@PathVariable String imp_uid, @RequestParam Long scheduleId, @RequestParam Long memberid, @RequestParam Long seatRow, @RequestParam Long seatColumn) throws IamportResponseException, IOException {
        try {
            IamportResponse<Payment> payment = iamportClient.paymentByImpUid(imp_uid);
            log.info("결제 요청 응답, {}", payment.getResponse().getMerchantUid());

            if (payment.getResponse().getStatus().equals("paid")) {
                Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new IllegalArgumentException("INVALID"));
                Member member = memberRepository.findById(memberid).orElseThrow(() -> new IllegalArgumentException("INVALID"));

                PayHistory payHistory = new PayHistory();
                payHistory.setScheduleId(schedule);
                payHistory.setMemberId(member);
                payHistory.setPrice(payment.getResponse().getAmount().longValue());
                payHistory.setRow(seatRow);
                payHistory.setColumn(seatColumn);
                payHistory.setTime(LocalDate.now());

                payHistoryRepository.save(payHistory);
                return ResponseEntity.ok("결제 성공 및 결제 정보 저장 완료");
            } else {
                return ResponseEntity.status(400).body("결제 실패");
            }
        } catch (Exception e) {
            log.error("Error", e);
            return ResponseEntity.status(400).body("잘못된 요청이다." + e.getMessage());
        }
    }

    @GetMapping("/paymenthistory/{memberId}")       // 결제 내역 조회
    public ResponseEntity<?> paymentList(@PathVariable Long memberId) {
        Optional<PayHistory> history = payHistoryRepository.findById(memberId);
        return ResponseEntity.ok(history);
    }
}
