package com.clonemovie.demo.controller;

import com.clonemovie.demo.DTO.PayHistoryDTO;
import com.clonemovie.demo.DTO.PayHistoryListDTO;
import com.clonemovie.demo.DemoApplication;
import com.clonemovie.demo.domain.Member;
import com.clonemovie.demo.domain.PayHistory;
import com.clonemovie.demo.domain.Schedule;
import com.clonemovie.demo.repository.MemberRepository;
import com.clonemovie.demo.repository.PayHistoryRepository;
import com.clonemovie.demo.repository.ScheduleRepository;
import com.clonemovie.demo.service.MemberService;
import com.clonemovie.demo.service.PayHistoryService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
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
    private final PayHistoryService payHistoryService;
    private IamportClient iamportClient;
    private MemberService memberService;

    @Value("${spring.iamport.API_KEY}")
    private String apiKey;

    @Value("${spring.iamport.API_SECRETKEY}")
    private String apiSecretKey;

    @PostConstruct
    public void init() {
        this.iamportClient = new IamportClient(apiKey, apiSecretKey);
    }

    @PostMapping(value = "/order/payment/{imp_uid}", consumes = MediaType.APPLICATION_JSON_VALUE)        // 결제 요청
    public ResponseEntity<?> validatePayment(@PathVariable String imp_uid, @RequestBody PayHistoryDTO request) throws IamportResponseException, IOException {
        PayHistory payhistory = payHistoryService.processPayment(request);
        log.info("Received payment request: {}", request);

        return ResponseEntity.ok(payhistory);
    }

    @GetMapping("/paymenthistory/{memberId}")       // 결제 내역 조회
    public ResponseEntity<List<PayHistory>> paymentList(@RequestBody PayHistoryListDTO request) {
        List<PayHistory> payhistory = payHistoryService.paymentList(request.getUserId());

        return ResponseEntity.ok(payhistory);
    }
}
