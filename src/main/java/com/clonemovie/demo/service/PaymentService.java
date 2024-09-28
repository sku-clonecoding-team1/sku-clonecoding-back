package com.clonemovie.demo.service;

import com.clonemovie.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentService {
    private final MemberRepository memberRepository;

}
