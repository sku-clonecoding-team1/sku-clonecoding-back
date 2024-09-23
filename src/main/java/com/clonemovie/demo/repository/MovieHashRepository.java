package com.clonemovie.demo.repository;
import com.clonemovie.demo.domain.MovieHash;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MovieHashRepository extends JpaRepository<MovieHash, Long> {
    // 가장 최근의 해시 값을 가져오기 위한 메서드
    Optional<MovieHash> findTopByOrderByCreatedAtDesc();
}