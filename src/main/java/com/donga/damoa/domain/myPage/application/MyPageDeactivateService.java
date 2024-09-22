package com.donga.damoa.domain.myPage.application;

import com.donga.damoa.domain.member.dao.MemberRepository;
import com.donga.damoa.domain.member.domain.Member;
import com.donga.damoa.domain.myPage.dao.MyPageRepository;
import com.donga.damoa.domain.myPage.dto.MyPageRequest;
import com.donga.damoa.domain.myPage.dto.MyPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageDeactivateService extends MyPageServiceTemplate {

    private final MyPageRepository myPageRepository;
    private final MemberRepository memberRepository;

    //멤버 존재 여부 검증
    @Override
    protected void validateMember(Member member) {
        // findByMemberId가 Optional을 반환하므로, Member가 존재하지 않으면 예외를 던짐
        myPageRepository.findByMemberId(member.getId())
            .orElseThrow(() -> new IllegalArgumentException("Member not found."));
    }

    //계정 비활성화 후 저장
    @Override
    protected void processDeactivate(Member member) {
        member.deactivateAccount();  // 계정을 비활성화 상태로 변경
        memberRepository.save(member);  // 변경된 정보를 저장
    }

    //계정 수정 불가
    @Override
    protected void processUpdate(Member member, MyPageRequest request) {
        throw new UnsupportedOperationException("This service does not support update.");
    }

    //계정 조회 불가
    @Override
    protected MyPageResponse processGetMyPage(Member member) {
        throw new UnsupportedOperationException("This service does not support getMyPage.");
    }
}
