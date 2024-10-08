package com.clonemovie.demo.controller;

import com.clonemovie.demo.DTO.PaymentLogDTO;
import com.clonemovie.demo.domain.Member;
import com.clonemovie.demo.domain.PaymentLog;
import com.clonemovie.demo.service.MemberService;
import com.clonemovie.demo.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final MemberService memberService;


    @PostMapping("/checkSeat")
    public boolean checkSeat(@RequestHeader("Authorization") String bearerToken, @RequestBody PaymentLogDTO request) {
        // 일단 헤더에서 꺼내긴 했는데 로그인 기능이 있어야 사용가능
        //String token = bearerToken.replace("Bearer ", "");
        //Member member = memberService.tokenToMember(token).get();
        return paymentService.getPaymentLog(request);
    }


    // 결제 후 데이터를 저장하는 엔드포인트
    @PostMapping("/paymentLogging")
    public ResponseEntity<Map<String, Object>> paymentLogging(@RequestBody PaymentLogDTO request) {

        PaymentLog payment = paymentService.savePaymentLog(request);

        // 응답을 JSON 형식으로 변환
        Map<String, Object> response = new HashMap<>();
        response.put("status", "OK"); // 상태 메시지
        response.put("paymentId", payment.getId()); // 결제 ID

        return ResponseEntity.ok(response);
    }


}
