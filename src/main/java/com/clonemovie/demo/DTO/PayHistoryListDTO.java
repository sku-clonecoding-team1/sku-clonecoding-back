package com.clonemovie.demo.DTO;

import lombok.Data;

@Data
public class PayHistoryListDTO {
    private String userId;

    public PayHistoryListDTO(String userId) {
        this.userId = userId;
    }
}
