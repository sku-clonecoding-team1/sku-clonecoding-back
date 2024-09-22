package com.clonemovie.demo.controller;

import com.clonemovie.demo.DTO.CartDTO;
import com.clonemovie.demo.DTO.CartRequestDTO;
import com.clonemovie.demo.domain.Schedule;
import com.clonemovie.demo.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    /**
     *장바구니 담기
     * @param request
     * @param scheduleId
     * @return
     */
    @PostMapping("/add/{scheduleId}")
    public ResponseEntity<String> add(@Valid @RequestBody CartRequestDTO request, @PathVariable Long scheduleId) {
        Long createId = cartService.addCart(request, scheduleId);
        return ResponseEntity.ok("장바구니에 등록되었습니다. cart_id : " + createId);
    }


    /**
     * 내 장바구니 리스트
     * @param memberId
     * @return
     */
    @GetMapping("/{memberId}")
    public List<CartDTO> getMyCarts(@PathVariable Long memberId) {
        return cartService.allCarts(memberId);
    }




}
