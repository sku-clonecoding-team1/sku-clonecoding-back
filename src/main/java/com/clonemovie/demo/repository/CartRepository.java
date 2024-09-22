package com.clonemovie.demo.repository;

import com.clonemovie.demo.domain.Cart;
import com.clonemovie.demo.domain.Member;
import com.clonemovie.demo.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart ScheduleAndMember(Schedule schedule, Member member);

    List<Cart> findByMemberId(Long memberId);
}
