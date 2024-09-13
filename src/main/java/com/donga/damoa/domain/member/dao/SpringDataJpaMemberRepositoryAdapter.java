package com.donga.damoa.domain.member.dao;

import com.donga.damoa.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpringDataJpaMemberRepositoryAdapter implements MemberRepository {

    private final MemberJpaRepository repository;

    @Override
    public void save(Member member) {
        repository.save(member);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
}
