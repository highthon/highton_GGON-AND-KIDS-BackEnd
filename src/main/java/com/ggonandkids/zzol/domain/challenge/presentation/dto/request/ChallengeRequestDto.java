package com.ggonandkids.zzol.domain.challenge.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor @NoArgsConstructor
public class ChallengeRequestDto {
    private String title;
    private String body;
    private int time;
    private int attend;
}