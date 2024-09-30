package com.clonemovie.demo.service;

import com.clonemovie.demo.domain.Member;
import com.clonemovie.demo.exception.InvalidIdorPassword;
import com.clonemovie.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
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
