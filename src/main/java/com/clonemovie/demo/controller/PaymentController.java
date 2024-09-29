package com.clonemovie.demo.controller;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {
//    private IamportClient iamportClient;
//
//    @Value("${spring.iamport.API_KEY}")
//    private String apiKey;
//
//    @Value("${spring.iamport.API_SECRETKEY}")
//    private String apiSecretKey;
//
//    @PostConstruct
//    public void init() {
//        this.iamportClient = new IamportClient(apiKey, apiSecretKey);
//    }
//
//    @PostMapping("/order/payment/{imp_uid}")        // 결제 요청
//    public IamportResponse<Payment> validatePayment(@PathVariable String imp_uid) throws IamportResponseException, IOException {
//        IamportResponse<Payment> payment = iamportClient.paymentByImpUid(imp_uid);
//        log.info("결제 요청 응답, {}", payment.getResponse().getMerchantUid());
//        return payment;
//    }
//
//    @GetMapping("/paymenthistory/{memberId}")       // 결제 내역 조회
//    public ResponseEntity<?> paymentList(@PathVariable Long memberId) {
//        return ResponseEntity.ok("성공");
//    }
}
