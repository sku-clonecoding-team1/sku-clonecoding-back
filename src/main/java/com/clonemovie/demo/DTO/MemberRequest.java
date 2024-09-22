package com.clonemovie.demo.DTO;

import lombok.Data;

@Data
public class MemberRequest {
    private String username;
    private String email;
    private String address;
}
