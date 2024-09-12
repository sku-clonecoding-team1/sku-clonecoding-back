package com.clonemovie.demo.service;

import com.clonemovie.demo.domain.Member;
import com.clonemovie.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private MemberRepository memberRepository;

    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    public List<Member> getAllUsers() {
        return memberRepository.findAll();
    }

    public void deleteMemberById(Long id) {
        memberRepository.deleteById(id);
    }
}
