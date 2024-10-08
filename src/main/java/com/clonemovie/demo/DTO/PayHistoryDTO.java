package com.clonemovie.demo.DTO;

import com.clonemovie.demo.domain.Member;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class PayHistoryDTO {
    private Long scheduleId;
    private Long memberId;
    private Long buyPrice;
    private Long rw;
    private Long col;
    private LocalDateTime paytime;

    public PayHistoryDTO(Long scheduleId, Long memberId, Long buyPrice, Long rw, Long col, LocalDate paytime) {
        this.scheduleId = scheduleId;
        this.memberId = memberId;
        this.buyPrice = buyPrice;
        this.rw = rw;
        this.col = col;
        this.paytime = LocalDateTime.now();
    }
}
