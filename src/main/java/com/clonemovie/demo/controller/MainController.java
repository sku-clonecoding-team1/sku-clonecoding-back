package com.clonemovie.demo.controller;

import com.clonemovie.demo.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final PaymentService payMentService;

    public MainController(PaymentService payMentService) {
        this.payMentService = payMentService;
    }

    @GetMapping("/index")
    public String index() {
        return "index";  // src/main/resources/templates/index.html 파일을 반환
    }



}
