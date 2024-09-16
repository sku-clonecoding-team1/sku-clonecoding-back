package com.clonemovie.demo.DTO;

import lombok.Data;

import java.time.LocalDate;

public class RegisterDTO {

    @Data
    public static class MemberCreateRequest {
        private String userId;
        private String password;
        private String email;
        private LocalDate birth;
    }
}
