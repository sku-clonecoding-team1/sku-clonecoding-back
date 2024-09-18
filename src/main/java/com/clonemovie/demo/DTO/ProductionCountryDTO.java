package com.clonemovie.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductionCountryDTO {
    private String iso3166_1;  // ISO 국가 코드
    private String name;  // 국가 이름
}
