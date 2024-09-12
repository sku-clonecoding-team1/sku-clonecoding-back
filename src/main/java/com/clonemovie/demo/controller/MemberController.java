package com.clonemovie.demo.controller;

import com.clonemovie.demo.DTO.LoginDTO;
import com.clonemovie.demo.DTO.SignUpDTO;
import com.clonemovie.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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



}
