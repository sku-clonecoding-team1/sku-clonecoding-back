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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {
    private final PayHistoryService payHistoryService;
    private IamportClient iamportClient;

    @Value("${spring.iamport.API_KEY}")
    private String apiKey;

    @Value("${spring.iamport.API_SECRETKEY}")
    private String apiSecretKey;

    @PostConstruct
    public void init() {
        this.iamportClient = new IamportClient(apiKey, apiSecretKey);
    }

    @Operation(summary = "결제히스토리 저장", description = "header에 bearer 토큰 필요,선택된 영화 스케쥴 id, memberId, 가격, 열, 행", tags = "pay",
            responses = {@ApiResponse(responseCode = "200", description = "결제 성공 후 정보 db에 저장")
                        })
    @PostMapping(value = "/order/payment/", consumes = MediaType.APPLICATION_JSON_VALUE)        // 결제 요청
    public ResponseEntity<?> validatePayment(@RequestBody PayHistoryDTO request) throws IamportResponseException, IOException {
        PayHistory payhistory = payHistoryService.processPayment(request);
        log.info("Received payment request: {}", request);

        return ResponseEntity.ok(payhistory);
    }

    @Operation(summary = "예약한 정보 조회", description = "Header에 bearer토큰 필요", tags = "pay",
            responses = {@ApiResponse(responseCode = "200", description = "회원이 결제한 정보 출력")})
    @GetMapping("/paymenthistory")       // 결제 내역 조회
    public ResponseEntity<List<PayHistory>> paymentList(@RequestHeader("Authorization") String bearerToken) {
        String token = bearerToken.replace("Bearer", "");
        List<PayHistory> payhistory = payHistoryService.paymentList(token);

        return ResponseEntity.ok(payhistory);
    }
}
