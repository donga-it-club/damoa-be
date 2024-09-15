package com.donga.damoa.domain.member.dao;

import com.donga.damoa.domain.member.domain.Member;
import java.util.Optional;

public interface MemberRepository {

    void save(Member member);

    boolean existsByEmail(String email);

    Optional<Member> findMemberByEmail(String email);

}
