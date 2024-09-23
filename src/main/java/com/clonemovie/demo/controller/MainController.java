package com.clonemovie.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/index")
    public String index() {
        return "index";  // src/main/resources/templates/index.html 파일을 반환
    }



    @GetMapping("/seatDetail")
    public String seatDetail(@RequestParam("seatID") String seatID, Model model) {
        // seatID 정보를 이용해 필요한 데이터 처리 로직 작성
        // 예: 해당 좌석의 상태 정보를 DB에서 조회하는 로직

        // seatID를 모델에 추가하여 뷰로 전달
        model.addAttribute("seatID", seatID);

        // seatDetail.html로 이동 (뷰 파일 이름)
        return "ticketDetail";
    }

}
