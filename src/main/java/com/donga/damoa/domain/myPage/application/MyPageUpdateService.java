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
public class MyPageUpdateService extends MyPageServiceTemplate {

    private final MemberRepository memberRepository;
    private final MyPageRepository myPageRepository;

    @Override
    protected void validateMember(Member member) {
        if (memberRepository.existsById(member.getId())) {
            throw new IllegalArgumentException("Member not found.");
        }
    }

    @Override
    protected void processUpdate(Member member, MyPageRequest request) {
        var myPage = myPageRepository.findByMemberId(member.getId())
            .orElseThrow(() -> new IllegalArgumentException("MyPage not found."));

        // 재학 여부 업데이트
        if (request.getEnrollmentStatus() != null) {
            myPage.updateEnrollmentStatus(request.getEnrollmentStatus());
        }

        // 비밀번호 업데이트 처리 (비밀번호 인코딩 필요)
        if (request.getPassword() != null) {
            member.setPassword(request.getPassword()); // 패스워드 인코딩 로직 추가 필요
        }
    }

    @Override
    protected MyPageResponse processGetMyPage(Member member) {
        throw new UnsupportedOperationException("This service does not support getMyPage.");
    }

    @Override
    protected void processDeactivate(Member member) {
        throw new UnsupportedOperationException("This service does not support deactivate.");
    }
}
