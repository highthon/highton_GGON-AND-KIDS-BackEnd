package com.ggonandkids.zzol.domain.user.presentation.dto.response;

import com.ggonandkids.zzol.domain.user.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberInfoResponseDto {
    private Long memberId;
    private String id;

    @Builder
    public MemberInfoResponseDto(Member member) {
        this.memberId = member.getMemberId();
        this.id = member.getId();
    }
}
