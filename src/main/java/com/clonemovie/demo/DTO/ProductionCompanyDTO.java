package com.clonemovie.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductionCompanyDTO {
    private int id;  // 제작사 ID
    private String logoPath;  // 제작사 로고 경로
    private String name;  // 제작사 이름
    private String originCountry;  // 제작 국가
}
