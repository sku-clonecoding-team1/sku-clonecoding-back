package com.clonemovie.demo.controller;

import com.clonemovie.demo.DTO.PaymentLogDTO;
import com.clonemovie.demo.domain.PaymentLog;
import com.clonemovie.demo.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;


    // 결제 전 데이터를 저장하고 상태를 0으로 설정하는 엔드포인트
    @PostMapping("/paymentLogging")
    public ResponseEntity<String> paymentLogging(@RequestBody PaymentLogDTO request) {

        System.out.println(request.getSeatID() + "   ---------   " + request.getMovieName());

        PaymentLog payment = paymentService.savePaymentLog(request);

        System.out.println( payment.getId() + "   ---------   " );


        return ResponseEntity.ok("Payment initialized with ID: " + payment.getId());
    }

    // 결제 후 상태를 업데이트하는 엔드포인트
//    @PostMapping("/complete")
//    public ResponseEntity<String> completePayment(@RequestParam Long paymentId, @RequestParam boolean isSuccess) {
//        paymentService.completePayment(paymentId, isSuccess);
//        return ResponseEntity.ok("Payment status updated.");
//    }


}
