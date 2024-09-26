package com.clonemovie.demo.controller;

import com.clonemovie.demo.DTO.PaymentLogDTO;
import com.clonemovie.demo.domain.PaymentLog;
import com.clonemovie.demo.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;


    @PostMapping("/checkSeat")
    public boolean checkSeat(@RequestBody PaymentLogDTO request) {
        return paymentService.getPaymentLog(request);
    }


    // 결제 후 데이터를 저장하는 엔드포인트
    @PostMapping("/paymentLogging")
    public ResponseEntity<String> paymentLogging(@RequestBody PaymentLogDTO request) {

        PaymentLog payment = paymentService.savePaymentLog(request);

        return ResponseEntity.ok("Payment initialized with ID: " + payment.getId());
    }



}
