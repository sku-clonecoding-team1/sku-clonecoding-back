package com.clonemovie.demo.DTO;

import com.clonemovie.demo.domain.Member;
import lombok.Data;

import java.util.Optional;

@Data
public class MemberInfo {
    private String userId;
    private String userName;

    public MemberInfo(Optional<Member> member) {
        if (member.isPresent()) {
            this.userId = member.get().getUserId();
            this.userName = member.get().getNickName();
        }
    }
}
