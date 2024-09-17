package com.donga.damoa.domain.myPage.dao;

import com.donga.damoa.domain.myPage.domain.MyPage;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MyPageJpaRepository extends JpaRepository<MyPage, Long> {

    @Query("SELECT mp FROM MyPage mp JOIN FETCH mp.projects WHERE mp.member.id = :memberId")
    Optional<MyPage> findByMemberIdWithProjects(@Param("memberId") Long memberId);

    Optional<MyPage> findByMemberId(Long memberId);
}
