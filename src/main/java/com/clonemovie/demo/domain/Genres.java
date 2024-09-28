package com.clonemovie.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Genres {
    @Id
    private Long id;
    private String name;
}
