package com.clonemovie.demo.DTO;

import lombok.Data;

@Data
public class PaymentLogDTO {
    private String seatID;
    private String movieName;
    private String cinemaName;
    private Long price;
    private Long movieId;
    private Long cinemaId;
}
