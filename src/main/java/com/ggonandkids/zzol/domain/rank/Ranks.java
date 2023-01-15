package com.ggonandkids.zzol.domain.rank;

import com.ggonandkids.zzol.domain.challenge.Challenge;
import com.ggonandkids.zzol.domain.user.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Ranks {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rankerId;

    private float time;

    @ManyToOne
    private Challenge challenge;

    @OneToOne
    private Member member;

    @Builder
    public Ranks(float time, Challenge challenge, Member member) {
        this.time = time;
        this.challenge = challenge;
        this.member = member;
    }
}
