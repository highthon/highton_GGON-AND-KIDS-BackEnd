package com.ggonandkids.zzol.domain.rank.presentation;

import com.ggonandkids.zzol.domain.challenge.Challenge;
import com.ggonandkids.zzol.domain.challenge.domain.repository.ChallengeRepository;
import com.ggonandkids.zzol.domain.rank.presentation.dto.request.RankRequestDto;
import com.ggonandkids.zzol.domain.rank.presentation.dto.response.RankResponseDto;
import com.ggonandkids.zzol.domain.rank.service.RankService;
import com.ggonandkids.zzol.domain.user.Member;
import com.ggonandkids.zzol.domain.user.domain.repository.MemberRepository;
import com.ggonandkids.zzol.global.annotation.AuthToken;
import com.ggonandkids.zzol.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rank")
@RequiredArgsConstructor
public class RankController {
    private final RankService rankService;
    private final ChallengeRepository challengeRepository;
    private final MemberRepository memberRepository;

    @AuthToken
    @PostMapping("/create/{challengeId}")
    public RankResponseDto create(@RequestBody RankRequestDto requestDto, @PathVariable Long challengeId, @RequestAttribute Member member){
        Challenge challenge = challengeRepository.findByChallengeId(challengeId)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다."));
        return rankService.create(requestDto, member, challenge);
    }
    @GetMapping("/all")
    public List<RankResponseDto> findRakeAll(){
        return rankService.findAllBy();
    }
    @GetMapping("/{challengeId}")
    public List<RankResponseDto> findAllRakeId(@PathVariable Long challengeId){
        return rankService.findAllByChallenge(challengeId);
    }
}
