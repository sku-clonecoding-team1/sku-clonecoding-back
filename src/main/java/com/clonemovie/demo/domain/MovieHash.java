package com.clonemovie.demo.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class MovieHash {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hashValue;  // 첫 번째 페이지 JSON 데이터의 해시값 저장
    private String createdAt;  // 해시가 저장된 날짜
}
