package com.clonemovie.demo.controller;

import com.clonemovie.demo.configuration.PortOneConfig;
import com.clonemovie.demo.service.CinemaMapper;
import com.clonemovie.demo.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final PortOneConfig portOneConfig;
    private final MovieService movieService;

    // 현재 controller에 있는 모든 메소드는 Spring에서 테스트 해보기 위함
    @GetMapping("/index")
    public String index() {
        return "index";  // src/main/resources/templates/index.html 파일을 반환
    }


    // 테스트 view단에 정보 보내기
    @GetMapping("/seatDetail")
    public String seatDetail(@RequestParam("seatID") String seatID,
                             @RequestParam("movieId") long movieId,
                             @RequestParam("cinemaId") int cinemaId,
                             Model model) {
        model.addAttribute("seatID", seatID);
        model.addAttribute("movieId", movieId);
        model.addAttribute("movieName", movieService.getMovieTitleToMovieId(movieId));
        model.addAttribute("cinemaId", cinemaId );
        model.addAttribute("cinemaName", CinemaMapper.getCinemaName(cinemaId) );
        // seatDetail.html로 이동 (뷰 파일 이름)

        return "ticketDetail";
    }
    
    
    // 테스트 단에 영화관 정보를 전달
    @GetMapping("/cinemas")
    public ResponseEntity<Map<Integer, String>> getCinemas() {
        Map<Integer, String> cinemas = CinemaMapper.getCinemaMap();
        return ResponseEntity.ok(cinemas); // 정상 응답 반환
    }


    @GetMapping("/api/get-imp-code")
    public ResponseEntity<String> getImpCode() {
        return ResponseEntity.ok(portOneConfig.getImpCode());
    }


}
