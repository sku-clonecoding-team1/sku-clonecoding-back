package com.clonemovie.demo.DTO;

import lombok.Data;

public class SignUpDTO {
    @Data
    public static class SignUpRequest{
        private String userId;
        private String password;
        private String nickName;
        private String email;
    }

}
