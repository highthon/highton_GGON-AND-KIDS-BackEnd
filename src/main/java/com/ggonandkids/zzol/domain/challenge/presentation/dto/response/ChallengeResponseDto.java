package com.ggonandkids.zzol.domain.challenge.presentation.dto.response;

import com.ggonandkids.zzol.domain.challenge.Challenge;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChallengeResponseDto {
    private Long challengeId;
    private String title;
    private String body;
    private float time;
    private int attend;
    private String id;

    public ChallengeResponseDto(Challenge challenge) {
        this.title = challenge.getTitle();
        this.body = challenge.getBody();
        this.time = challenge.getTime();
        this.attend = challenge.getAttend();
        this.id = challenge.getMember().getId();
    }
}
