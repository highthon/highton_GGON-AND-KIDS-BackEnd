package com.ggonandkids.zzol.domain.user;

import com.ggonandkids.zzol.global.exception.GlobalException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@Entity
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String id;
    private String password;

    @Builder
    public Member(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public static class NotFoundException extends GlobalException {
        public NotFoundException() {
            super(HttpStatus.BAD_REQUEST, "아아디또는 비밀번호가 틀렷습니다.");
        }
    }

    public static class AlreadyExistedException extends GlobalException {
        public AlreadyExistedException() {
            super(HttpStatus.CONFLICT, "이미 가입된 회원입니다.");
        }
    }
}
