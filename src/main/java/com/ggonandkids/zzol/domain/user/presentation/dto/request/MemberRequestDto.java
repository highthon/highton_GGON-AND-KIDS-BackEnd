package com.ggonandkids.zzol.domain.user.presentation.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberRequestDto {
    private String id;
    private String password;

    @Builder
    public MemberRequestDto(String id, String password) {
        this.id = id;
        this.password = password;
    }
}
