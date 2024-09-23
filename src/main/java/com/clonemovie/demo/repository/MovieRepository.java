package com.clonemovie.demo.repository;

import com.clonemovie.demo.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
