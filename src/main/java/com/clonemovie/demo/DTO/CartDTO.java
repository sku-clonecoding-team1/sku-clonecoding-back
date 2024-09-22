package com.clonemovie.demo.DTO;


import com.clonemovie.demo.domain.Cart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDTO {
    private Long id;
    private Long memberId;
    private String memberEmail;
    private Long ticketName;

    public CartDTO(Cart cart) {
        this(
                cart.getId(),
                cart.getMember().getId(),
                cart.getMember().getEMail(),
                cart.getSchedule().getMovieId()
        );
    }


    public static CartDTO fromEntity(Cart cart) {
        return CartDTO.builder()
                .id(cart.getId())
                .memberId(cart.getMember().getId())
                .memberEmail(cart.getMember().getEMail())
                .ticketName(cart.getSchedule().getMovieId())
                .build();
    }

}
