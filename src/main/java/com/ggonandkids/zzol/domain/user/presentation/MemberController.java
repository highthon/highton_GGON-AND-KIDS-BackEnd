package com.ggonandkids.zzol.domain.user.presentation;

import com.ggonandkids.zzol.domain.user.Member;
import com.ggonandkids.zzol.domain.user.presentation.dto.request.MemberRequestDto;
import com.ggonandkids.zzol.domain.user.presentation.dto.response.MemberInfoResponseDto;
import com.ggonandkids.zzol.domain.user.presentation.dto.response.MemberResponseDto;
import com.ggonandkids.zzol.domain.user.service.MemberService;
import com.ggonandkids.zzol.global.annotation.AuthToken;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public Member signup(@RequestBody MemberRequestDto requestDto){
        return memberService.save(requestDto);
    }

    @PostMapping("/login")
    private MemberResponseDto login(@RequestBody MemberRequestDto requestDto){
        return memberService.login(requestDto);
    }

    @AuthToken
    @GetMapping("/read")
    private MemberInfoResponseDto findOne(@RequestAttribute Member member){
        return new MemberInfoResponseDto(member);
    }
}
