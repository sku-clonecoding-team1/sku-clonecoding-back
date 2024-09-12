package com.clonemovie.demo.service;

import com.clonemovie.demo.DTO.LoginDTO;
import com.clonemovie.demo.DTO.SignUpDTO;
import com.clonemovie.demo.domain.Member;
import com.clonemovie.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtUtility jwtUtility;

    @Transactional
    public Member saveMember(SignUpDTO.SignUpRequest request) {

        Member member = new Member(request);
        return memberRepository.save(member);
    }

    public Optional<Member> tokenToMember(String token) {
        return memberRepository.findByUserId(jwtUtility.validateToken(token).getSubject());
    }

    public Member findMemberByUserId(String userId) {
        return memberRepository.findByUserId(userId).orElse(null);
    }

    public String logInMember(LoginDTO.LoginRequest request) {
        Member member = findMemberByUserId(request.getUserId());
        if ( member != null && member.checkPassword(request.getPassword())) {
            return jwtUtility.generateToken(request.getUserId());
        }else{
            return null;
        }
    }




}
