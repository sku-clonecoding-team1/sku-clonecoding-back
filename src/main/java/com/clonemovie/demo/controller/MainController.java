package com.clonemovie.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/index")
    public String index() {
        return "index";  // src/main/resources/templates/index.html 파일을 반환
    }



}
