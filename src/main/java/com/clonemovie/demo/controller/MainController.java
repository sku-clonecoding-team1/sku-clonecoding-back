package com.clonemovie.demo.controller;

import com.clonemovie.demo.service.CinemaMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {


    // 현재 controller에 있는 모든 메소드는 Spring에서 테스트 해보기 위함
    @GetMapping("/index")
    public String index() {
        return "index";  // src/main/resources/templates/index.html 파일을 반환
    }


    // 테스트 view단에 정보 보내기
    @GetMapping("/seatDetail")
    public String seatDetail(@RequestParam("seatID") String seatID, Model model) {
        model.addAttribute("seatID", seatID);
        // seatDetail.html로 이동 (뷰 파일 이름)
        return "ticketDetail";
    }
    
    
    // 테스트 단에 영화관 정보를 전달
    @GetMapping("/cinemas")
    public ResponseEntity<Map<Integer, String>> getCinemas() {
        Map<Integer, String> cinemas = CinemaMapper.getCinemaMap();
        return ResponseEntity.ok(cinemas); // 정상 응답 반환
    }


}
