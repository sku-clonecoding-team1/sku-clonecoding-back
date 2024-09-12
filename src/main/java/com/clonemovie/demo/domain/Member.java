package com.clonemovie.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userId;
    private String password;

    @Setter
    @Column(unique = true)
    private String email;

    @Setter @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;

    public Member(String userId, String password, String email, LocalDate birth) {
        this.userId = userId;
        this.setPassword(password);
        this.email = email;
        this.birth = birth;
    }

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void setPassword(String password) {this.password = passwordEncoder.encode(password);}

    public boolean checkPassword(String password) { return passwordEncoder.matches(password, this.password);}
}
