package com.ggonandkids.zzol.domain.challenge.service;

import com.ggonandkids.zzol.domain.challenge.Challenge;
import com.ggonandkids.zzol.domain.challenge.domain.repository.ChallengeRepository;
import com.ggonandkids.zzol.domain.challenge.presentation.dto.request.ChallengeRequestDto;
import com.ggonandkids.zzol.domain.challenge.presentation.dto.response.ChallengeResponseDto;
import com.ggonandkids.zzol.domain.user.Member;
import com.ggonandkids.zzol.global.exception.GlobalException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ChallengeService {
    private final ChallengeRepository challengeRepository;

    @Transactional
    public Challenge create(ChallengeRequestDto requestDto, Member member){
        Challenge challenge = Challenge.builder()
                .title(requestDto.getTitle())
                .body(requestDto.getBody())
                .time(requestDto.getTime())
                .attend(0)
                .member(member)
                .build();
        return challengeRepository.save(challenge);
    }

    @Transactional
    public ChallengeResponseDto findChallengeOne(Long challengeId){
        Challenge challenge = challengeRepository.findByChallengeId(challengeId)
                .orElseThrow(() ->
                        new GlobalException(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다.")
                );
        return new ChallengeResponseDto(challenge);
    }

    @Transactional
    public List<ChallengeResponseDto> findChallengeAll(){
        return challengeRepository.findAll()
                .stream()
                .map(ChallengeResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long challengeId){
        challengeRepository.deleteById(challengeId);
    }

    @Transactional
    public void attendUp(Long challengeId){
        Challenge challenge = challengeRepository.findById(challengeId)
                .orElseThrow(() ->
                        new GlobalException(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다.")
                );
        int challengeAttend = challenge.getAttend() + 1;
        challenge.setAttend(challengeAttend);
    }
}
