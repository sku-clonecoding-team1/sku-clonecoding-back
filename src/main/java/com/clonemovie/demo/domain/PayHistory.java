package com.clonemovie.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
public class PayHistory {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie_id;
    private Long price;
    private Long row;
    private Long column;
    private LocalDate time;
}
