package com.clonemovie.demo.repository;

import com.clonemovie.demo.domain.MovieGenres;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieGenresRepository extends JpaRepository<MovieGenres, Long> {

}
