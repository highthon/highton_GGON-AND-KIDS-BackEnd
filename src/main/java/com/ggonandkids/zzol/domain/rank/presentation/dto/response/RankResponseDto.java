package com.ggonandkids.zzol.domain.rank.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ggonandkids.zzol.domain.challenge.Challenge;
import com.ggonandkids.zzol.domain.rank.Ranks;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RankResponseDto {
    private float time;
    private Challenge challenge;
    @JsonProperty(index = 1)
    private String id;

    public RankResponseDto(Ranks rank) {
        this.time = rank.getTime();
        this.challenge = rank.getChallenge();
        this.id = rank.getMember().getId();
    }
}
