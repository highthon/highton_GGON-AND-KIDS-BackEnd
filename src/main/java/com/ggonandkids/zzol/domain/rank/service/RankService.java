package com.ggonandkids.zzol.domain.rank.service;

import com.ggonandkids.zzol.domain.challenge.Challenge;
import com.ggonandkids.zzol.domain.challenge.service.ChallengeService;
import com.ggonandkids.zzol.domain.rank.Ranks;
import com.ggonandkids.zzol.domain.rank.domain.repository.RankRepository;
import com.ggonandkids.zzol.domain.rank.presentation.dto.request.RankRequestDto;
import com.ggonandkids.zzol.domain.rank.presentation.dto.response.RankResponseDto;
import com.ggonandkids.zzol.domain.user.Member;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RankService {
    private final RankRepository rankRepository;
    private final ChallengeService challengeService;

    @Transactional
    public RankResponseDto create(RankRequestDto requestDto, Member member, Challenge challenge){
        if(!rankRepository.existsByMember(member)) {
            challengeService.attendUp(challenge.getChallengeId());
        }
            Ranks rank = Ranks.builder()
                    .time(requestDto.getTime())
                    .challenge(challenge)
                    .member(member)
                    .build();
            rankRepository.save(rank);
            return new RankResponseDto(rank);
    }
    @Transactional
    public List<RankResponseDto> findAllBy(){
        return rankRepository.findAll()
                .stream()
                .map(RankResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<RankResponseDto> findAllByChallenge(Long challengeId){
        return rankRepository.findAllByChallengeChallengeId(challengeId)
                .stream()
                .map(RankResponseDto::new)
                .collect(Collectors.toList());
    }
}
