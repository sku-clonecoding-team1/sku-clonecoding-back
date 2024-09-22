package com.clonemovie.demo.DTO;

import com.clonemovie.demo.domain.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequestDTO {
    private Long memberId;

    public CartRequestDTO(Cart cart) {
        this.memberId = cart.getMember().getId();
    }
}
