package com.ggonandkids.zzol.domain.challenge.presentation;

import com.ggonandkids.zzol.domain.challenge.Challenge;
import com.ggonandkids.zzol.domain.challenge.presentation.dto.request.ChallengeRequestDto;
import com.ggonandkids.zzol.domain.challenge.presentation.dto.response.ChallengeResponseDto;
import com.ggonandkids.zzol.domain.challenge.service.ChallengeService;
import com.ggonandkids.zzol.domain.user.Member;
import com.ggonandkids.zzol.domain.user.domain.repository.MemberRepository;
import com.ggonandkids.zzol.global.response.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenge")
@RequiredArgsConstructor
public class ChallengeController {
    private final ChallengeService challengeService;
    private final MemberRepository memberRepository;
    private final ResponseService responseService;

    @PostMapping("/create/{id}")
    public Challenge create(@RequestBody ChallengeRequestDto requestDto, @PathVariable String id){
        Member member = memberRepository.findById(id);
        return challengeService.create(requestDto, member);
    }

    @GetMapping("/one/{challengeId}")
    private ChallengeResponseDto readOne(@PathVariable Long challengeId){
        return challengeService.findChallengeOne(challengeId);
    }

    @GetMapping("/all")
    public List<ChallengeResponseDto> readAll( ){
        return challengeService.findChallengeAll();
    }

    @DeleteMapping("/delete/{ChallengeId}")
    public void delete(@PathVariable Long ChallengeId){
        challengeService.delete(ChallengeId);
    }
}
