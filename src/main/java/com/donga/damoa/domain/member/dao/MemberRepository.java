package com.donga.damoa.domain.member.dao;

import com.donga.damoa.domain.member.domain.Member;

public interface MemberRepository {

    void save(Member member);

    boolean existsByEmail(String email);

}
