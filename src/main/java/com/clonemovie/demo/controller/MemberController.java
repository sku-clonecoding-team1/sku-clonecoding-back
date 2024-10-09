package com.clonemovie.demo.controller;

import com.clonemovie.demo.DTO.LoginDTO;
import com.clonemovie.demo.DTO.MemberInfo;
import com.clonemovie.demo.DTO.SignUpDTO;
import com.clonemovie.demo.domain.Member;
import com.clonemovie.demo.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody SignUpDTO.SignUpRequest request){
        if (memberService.saveMember(request) != null) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Success");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail");
        }
    }

    @PostMapping("/logIn")
    public ResponseEntity<String> logIn(@RequestBody LoginDTO.LoginRequest request){
        String token = memberService.logInMember(request);
        if ( token.length() != 0 ) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(token);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail");
        }
    }

    @Operation(summary = "회원 정보 조회", description = "header에 Bearer 토큰 필요", tags = "info",
            responses = {@ApiResponse(responseCode = "200", description = "userId, nickname 반환"),
                        @ApiResponse(responseCode = "500", description = "토큰오류")})
    @GetMapping("/info")
    public ResponseEntity<?> info(@RequestHeader("Authorization") String bearerToken) {
        String token = bearerToken.replace("Bearer ", "");
        Optional<Member> member = memberService.tokenToMember(token);
        return ResponseEntity.ok(new MemberInfo(member));
    }
}
