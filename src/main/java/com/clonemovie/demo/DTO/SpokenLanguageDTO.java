package com.clonemovie.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpokenLanguageDTO {
    private String englishName;  // 언어의 영어 이름
    private String iso639_1;  // ISO 언어 코드
    private String name;  // 언어 이름
}
