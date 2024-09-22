package com.clonemovie.demo.DTO;

import lombok.Builder;
import lombok.Data;

@Data
public class RequestPayDTO {
    private String orderUid;
    private Long ticket;
    private String buyerName;
    private Long paymentPrice;
    private String buyerEmail;
    private String buyerAddress;

    @Builder
    public RequestPayDTO(String orderUid, Long ticket, String buyerName, Long paymentPrice, String buyerEmail, String buyerAddress) {
        this.orderUid = orderUid;
        this.ticket = ticket;
        this.buyerName = buyerName;
        this.paymentPrice = paymentPrice;
        this.buyerEmail = buyerEmail;
        this.buyerAddress = buyerAddress;

    }
}
