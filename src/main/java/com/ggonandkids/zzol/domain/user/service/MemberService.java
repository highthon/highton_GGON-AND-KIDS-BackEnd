package com.ggonandkids.zzol.domain.user.service;

import com.ggonandkids.zzol.domain.user.Member;
import com.ggonandkids.zzol.domain.user.domain.repository.MemberRepository;
import com.ggonandkids.zzol.domain.user.presentation.dto.request.MemberRequestDto;
import com.ggonandkids.zzol.domain.user.presentation.dto.response.MemberResponseDto;
import com.ggonandkids.zzol.global.jwt.JwtAuth;
import com.ggonandkids.zzol.global.jwt.TokenProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;

    @Transactional
    public Member save(MemberRequestDto memberRequestDto){
        if(isExisted(memberRequestDto)){
            throw new  Member.AlreadyExistedException();
        }
        return memberRepository.save(
                Member.builder()
                .id(memberRequestDto.getId())
                .password(memberRequestDto.getPassword())
                .build());
    }

    @Transactional
    public MemberResponseDto login(MemberRequestDto requestDto){
        Member member = memberRepository.findByIdAndPassword(
                requestDto.getId(), requestDto.getPassword()).orElseThrow(Member.NotFoundException::new);
        Long memberId = member.getMemberId();
        return MemberResponseDto.builder()
                .member(member)
                .token(tokenProvider.generateToken(memberId, JwtAuth.ACCESS_TOKEN))
                .build();
    }


    //유저 가입 되어있는지 확인
    @Transactional
    protected boolean isExisted(MemberRequestDto requestDto) {
        return memberRepository.existsById(requestDto.getId());
    }
}
