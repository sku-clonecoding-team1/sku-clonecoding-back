package com.clonemovie.demo.service;

import com.clonemovie.demo.domain.Member;
import com.clonemovie.demo.exception.InvalidIdorPassword;
import com.clonemovie.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtUtility jwtUtility;

    @Transactional
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    public Member tokentoMember(String token) {
        Member member = memberRepository.findByUserId(jwtUtility.validateToken(token).getSubject());

        if (member == null) {
            throw new InvalidIdorPassword();
        }
        return member;
    }

    public Member findById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public Member findByUserId(String userId) {
        return memberRepository.findByUserId(userId);
    }

    public List<Member> getAllUsers() {
        return memberRepository.findAll();
    }

    @Transactional
    public void deleteMemberById(Long id) {
        memberRepository.deleteById(id);
    }

    @Transactional
    public String login(String userId, String password) {
        Member member = memberRepository.findByUserId(userId);
        if (member != null && member.checkPassword(password)) {
            return jwtUtility.generateToken(member.getUserId());
        }
        throw new InvalidIdorPassword();
    }
}
