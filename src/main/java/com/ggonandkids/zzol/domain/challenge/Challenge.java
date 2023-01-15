package com.ggonandkids.zzol.domain.challenge;

import com.ggonandkids.zzol.domain.user.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Challenge {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long challengeId;
    private String title;
    private String body;
    private float time;
    private int attend;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Builder
    public Challenge(String title, String body, float time, int attend, Member member) {
        this.title = title;
        this.body = body;
        this.time = time;
        this.attend = attend;
        this.member = member;
    }
}
