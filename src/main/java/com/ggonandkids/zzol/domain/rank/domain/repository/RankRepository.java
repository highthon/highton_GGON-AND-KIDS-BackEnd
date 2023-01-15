package com.ggonandkids.zzol.domain.rank.domain.repository;

import com.ggonandkids.zzol.domain.rank.Ranks;
import com.ggonandkids.zzol.domain.user.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankRepository extends JpaRepository<Ranks, Long> {
    boolean existsByMember(Member member);

    @Query(value = "SELECT p FROM Ranks p JOIN FETCH p.member m WHERE p.time <= (SELECT time FROM Challenge) ORDER BY p.time DESC")
    List<Ranks> findAll();
//    @Query(value = "SELECT p FROM Ranks p WHERE p.time <= (SELECT time FROM Challenge) ORDER BY p.time DESC")
    List<Ranks> findAllByChallengeChallengeId(Long challengeId);
}
