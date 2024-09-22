package com.clonemovie.demo.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable // <--- 기본형으로 추가함
@Getter
@Setter
@NoArgsConstructor
public class Seat {
    private int seatStatus;
}
