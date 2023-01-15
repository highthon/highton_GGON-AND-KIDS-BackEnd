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
    private Long memberId;
    private String id;
    private String password;

    @Builder
    public Member(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public static class NotFoundException extends GlobalException {
        public NotFoundException() {
            super(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다.");
        }
    }

    public static class UnAuthenticationException extends GlobalException {
        public UnAuthenticationException() {
            super(HttpStatus.UNAUTHORIZED, "토큰이 입력되지 않았습니다.");
        }
    }

    public static class ForbiddenException extends GlobalException {
        public ForbiddenException() {
            super(HttpStatus.FORBIDDEN, "접근할 수 있는 권한이 없습니다.");
        }
    }

    public static class AlreadyExistedException extends GlobalException {
        public AlreadyExistedException() {
            super(HttpStatus.CONFLICT, "이미 가입된 회원입니다.");
        }
    }
}
