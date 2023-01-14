package com.ggonandkids.zzol.domain.user.presentation;

import com.ggonandkids.zzol.domain.user.Member;
import com.ggonandkids.zzol.domain.user.presentation.dto.request.MemberRequestDto;
import com.ggonandkids.zzol.domain.user.presentation.dto.response.MemberInfoResponseDto;
import com.ggonandkids.zzol.domain.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private MemberInfoResponseDto login(@RequestBody MemberRequestDto requestDto){
        return memberService.login(requestDto);
    }
}
