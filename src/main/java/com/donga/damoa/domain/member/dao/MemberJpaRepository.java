package com.donga.damoa.domain.member.dao;

import com.donga.damoa.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

}
