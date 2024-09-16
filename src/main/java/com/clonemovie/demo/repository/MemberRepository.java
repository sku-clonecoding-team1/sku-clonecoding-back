package com.clonemovie.demo.repository;

import com.clonemovie.demo.domain.Member;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByUserId(String userId);
}
