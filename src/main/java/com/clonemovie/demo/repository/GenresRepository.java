package com.clonemovie.demo.repository;

import com.clonemovie.demo.domain.Genres;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenresRepository extends JpaRepository<Genres, Long> {

}
