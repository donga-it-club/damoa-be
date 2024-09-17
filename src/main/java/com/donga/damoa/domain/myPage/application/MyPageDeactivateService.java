package com.donga.damoa.domain.myPage.application;

import com.donga.damoa.domain.member.dao.MemberRepository;
import com.donga.damoa.domain.member.domain.Member;
import com.donga.damoa.domain.myPage.dto.MyPageRequest;
import com.donga.damoa.domain.myPage.dto.MyPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageDeactivateService extends MyPageServiceTemplate {

    private final MemberRepository memberRepository;

    @Override
    protected void validateMember(Member member) {
        if (memberRepository.existsById(member.getId())) {
            throw new IllegalArgumentException("Member not found.");
        }
    }

    @Override
    protected void processDeactivate(Member member) {
        member.deactivateAccount();
        memberRepository.save(member);
        // 추가적인 탈퇴 처리 로직이 필요하다면 여기에 추가 가능
    }

    @Override
    protected void processUpdate(Member member, MyPageRequest request) {
        throw new UnsupportedOperationException("This service does not support update.");
    }

    @Override
    protected MyPageResponse processGetMyPage(Member member) {
        throw new UnsupportedOperationException("This service does not support getMyPage.");
    }
}
