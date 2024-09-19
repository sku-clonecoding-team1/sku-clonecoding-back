package com.clonemovie.demo.DTO;

import com.clonemovie.demo.domain.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TheaterDTO {
    private Long id;
    private String name;
    private boolean adult;

    public TheaterDTO(Movie movie) {
        this.id = movie.getId();
        this.name = movie.getTitle();
        this.adult = movie.isAdult();
    }
}
