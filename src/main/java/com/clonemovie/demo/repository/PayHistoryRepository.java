package com.clonemovie.demo.repository;

import com.clonemovie.demo.domain.PayHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayHistoryRepository extends JpaRepository<PayHistory, Long> {
    List<PayHistory> findByMemberId_UserId(String userId);
}
