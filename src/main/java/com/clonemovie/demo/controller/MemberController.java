package com.clonemovie.demo.controller;

import com.clonemovie.demo.DTO.LoginDTO;
import com.clonemovie.demo.DTO.RegisterDTO.*;
import com.clonemovie.demo.domain.Member;
import com.clonemovie.demo.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "회원가입", description = "아이디, 비밀번호, 이메일, 생년월일", tags = "register",
            responses = {@ApiResponse(responseCode = "200", description = "회원가입 성공 후 토큰 반환"),
                        @ApiResponse(responseCode = "400", description = "아이디 또는 이메일이 중복되었습니다.")})
    @PostMapping("/member/add")
    public ResponseEntity<?> signup(@RequestBody MemberCreateRequest request) {
        memberService.saveMember(new Member(request.getUserId(), request.getPassword(), request.getEmail(), request.getBirth()));
        String token = memberService.login(request.getUserId(), request.getPassword());
        return ResponseEntity.ok(token);
    }

    @Operation(summary = "로그인", description = "아이디, 비밀번호 입력", tags = "login",
             responses = {@ApiResponse(responseCode = "200", description = "로그인 성공 후 토큰 반환"),
                         @ApiResponse(responseCode = "400", description = "아이디 또는 비밀번호가 틀렸습니다.")})
    @PostMapping("/member/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO.LoginRequest request) {
        String token = memberService.login(request.getUserId(), request.getPassword());
        return ResponseEntity.ok(token);
    }
}
