package com.donga.damoa.domain.member.dao;

import com.donga.damoa.domain.member.domain.Member;
import java.util.Optional;
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

    @Override
    public Optional<Member> findMemberByEmail(String email) {
        return repository.findMemberByEmail(email);
    }

    @Override
    public Optional<Member> findByIdWithMajor(Long memberId) {
        return repository.findMemberWithMajorsById(memberId);
    }

}
