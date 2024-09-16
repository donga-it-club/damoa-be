package com.donga.damoa.domain.member.dao;

import com.donga.damoa.domain.member.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);

    Optional<Member> findMemberByEmail(String email);

    @Query("SELECT m FROM Member m JOIN FETCH m.majors WHERE m.id = :memberId")
    Optional<Member> findMemberWithMajorsById(@Param("memberId") Long memberId);

}
