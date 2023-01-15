package com.ggonandkids.zzol.domain.user.domain.repository;

import com.ggonandkids.zzol.domain.user.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {
    boolean existsById(String id);
    boolean existsByIdAndPassword(String id, String password);
    Member findById(String id);
    Optional<Member> findByMemberId(Long memberId);
    Optional<Member> findByIdAndPassword(String id, String password);
}
