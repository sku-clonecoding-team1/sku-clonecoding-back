package com.clonemovie.demo.domain;


import com.clonemovie.demo.DTO.SignUpDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@NoArgsConstructor
@Getter
@Entity
public class Member {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String userId;

    private String password;
    @Setter
    private String nickName;
    private String eMail;
    private String address;


    public Member(SignUpDTO.SignUpRequest request) {
        this.userId = request.getUserId();
        this.setPassword(request.getPassword());
        this.nickName = request.getNickName();
        this.eMail = request.getEmail();
        this.address = request.getAddress();
    }



    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public void setPassword(String password){
        this.password = passwordEncoder.encode(password);
    }


    public boolean checkPassword(String password){
        return passwordEncoder.matches(password, this.password);
    }


}
