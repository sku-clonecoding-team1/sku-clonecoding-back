package com.clonemovie.demo.repository;

import com.clonemovie.demo.domain.PayHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayHistoryRepository extends JpaRepository<Long, PayHistory> {

}
