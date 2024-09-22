package com.clonemovie.demo.service;


import com.clonemovie.demo.DTO.CartDTO;
import com.clonemovie.demo.DTO.CartRequestDTO;
import com.clonemovie.demo.domain.Cart;
import com.clonemovie.demo.domain.Member;
import com.clonemovie.demo.domain.Schedule;
import com.clonemovie.demo.repository.CartRepository;
import com.clonemovie.demo.repository.MemberRepository;
import com.clonemovie.demo.repository.MovieScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class CartService {
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final MovieScheduleRepository movieScheduleRepository;

    public Long addCart(CartRequestDTO request, Long scheduleId) {
        Member member = memberRepository.findById(request.getMemberId()).orElseThrow(() -> new NoSuchElementException());
        Schedule schedule = movieScheduleRepository.findById(scheduleId).orElseThrow(() -> new NoSuchElementException());

        Cart existingCart = cartRepository.ScheduleAndMember(schedule, member);

        if (existingCart != null) {
            cartRepository.save(existingCart);
            return existingCart.getId();

        }else{
            Cart cart = new Cart(member, schedule );

            cartRepository.save(cart);
            return cart.getId();
        }
    }

    /**
     * 유저의 전체 장바구니 리스트 조회
     * @return
     */
    public List<CartDTO> allCarts(Long memberId) {
        List<Cart> carts = cartRepository.findByMemberId(memberId);
        return carts.stream()
                .map(CartDTO::fromEntity)
                .collect(Collectors.toList());
    }


}
