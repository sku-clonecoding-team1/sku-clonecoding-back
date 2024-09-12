package com.clonemovie.demo.repository;

import com.clonemovie.demo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {


    // userId로 Member를 찾는 메서드
    Optional<Member> findByUserId(String userId);


}
