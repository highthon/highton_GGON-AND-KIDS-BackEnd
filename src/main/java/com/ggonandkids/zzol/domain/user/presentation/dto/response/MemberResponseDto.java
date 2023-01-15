package com.ggonandkids.zzol.domain.user.presentation.dto.response;

import com.ggonandkids.zzol.domain.user.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberResponseDto {
    private Member member;
    private String token;
}
