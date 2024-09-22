package com.donga.damoa.domain.myPage.dao;

import com.donga.damoa.domain.myPage.domain.MyPage;
import java.util.Optional;

public interface MyPageRepository {

    void save(MyPage myPage);

    Optional<MyPage> findByMemberIdWithProjects(Long memberId);

    Optional<MyPage> findByMemberId(Long memberId);
}
