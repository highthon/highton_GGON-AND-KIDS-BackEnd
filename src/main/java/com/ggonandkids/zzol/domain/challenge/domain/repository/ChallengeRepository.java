package com.ggonandkids.zzol.domain.challenge.domain.repository;

import com.ggonandkids.zzol.domain.challenge.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    @Query("SELECT p FROM Challenge p JOIN FETCH p.member m ORDER BY p.attend DESC")
    List<Challenge> findAll();

    Optional<Challenge> findByChallengeId(Long challengeId);
}
