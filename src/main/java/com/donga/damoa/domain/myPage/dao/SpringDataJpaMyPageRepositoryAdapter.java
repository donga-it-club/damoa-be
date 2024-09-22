package com.donga.damoa.domain.myPage.dao;

import com.donga.damoa.domain.myPage.domain.MyPage;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpringDataJpaMyPageRepositoryAdapter implements MyPageRepository{
    private final MyPageJpaRepository repository;

    @Override
    public void save(MyPage myPage) {
        repository.save(myPage);}

    @Override
    public Optional<MyPage> findByMemberIdWithProjects(Long memberId) {
        return repository.findByMemberIdWithProjects(memberId);
    }

    @Override
    public Optional<MyPage> findByMemberId(Long memberId) {
        return repository.findByMemberId(memberId);
    }
}
