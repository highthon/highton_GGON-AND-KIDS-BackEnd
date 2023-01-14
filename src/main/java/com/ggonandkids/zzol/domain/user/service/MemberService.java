package com.ggonandkids.zzol.domain.user.service;

import com.ggonandkids.zzol.domain.user.Member;
import com.ggonandkids.zzol.domain.user.domain.repository.MemberRepository;
import com.ggonandkids.zzol.domain.user.presentation.dto.request.MemberRequestDto;
import com.ggonandkids.zzol.domain.user.presentation.dto.response.MemberInfoResponseDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member save(MemberRequestDto memberRequestDto){
        if(isExisted(memberRequestDto)){
            throw new  Member.AlreadyExistedException();
        }
        Member member = Member.builder()
                .id(memberRequestDto.getId())
                .password(memberRequestDto.getPassword())
                .build();
        return memberRepository.save(member);
    }

    @Transactional
    public MemberInfoResponseDto login(MemberRequestDto requestDto){
        Member member = memberRepository.findByIdAndPassword(
                requestDto.getId(), requestDto.getPassword()).orElseThrow(Member.NotFoundException::new);
        return new MemberInfoResponseDto(member.getUserId(), member.getId());
    }

    @Transactional
    public MemberInfoResponseDto findBy(String id){
        Member member = memberRepository.findById(id);
        return MemberInfoResponseDto.builder()
                .userId(member.getUserId())
                .id(id)
                .build();
    }

    //유저 가입 되어있는지 확인
    @Transactional
    protected boolean isExisted(MemberRequestDto requestDto) {
        return memberRepository.existsById(requestDto.getId());
    }
}
