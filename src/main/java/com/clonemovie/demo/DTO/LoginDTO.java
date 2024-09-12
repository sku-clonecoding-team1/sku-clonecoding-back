package com.clonemovie.demo.DTO;


import lombok.Data;

public class LoginDTO {

    @Data
    public static class LoginRequest{
        private String userId;
        private String password;
    }


}
