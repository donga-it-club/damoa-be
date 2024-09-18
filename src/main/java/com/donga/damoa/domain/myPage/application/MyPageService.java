package com.donga.damoa.domain.myPage.application;

import com.donga.damoa.domain.myPage.dao.MyPageRepository;
import com.donga.damoa.domain.myPage.dto.MyPageRequest;
import com.donga.damoa.domain.member.dao.MemberRepository;
import com.donga.damoa.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final MemberRepository memberRepository;
    private final MyPageRepository myPageRepository;
    private final PasswordEncoder passwordEncoder;

    //MyPage 수정
    @Transactional
    public void updateMyPage(Member member, MyPageRequest request) {
        // MyPage 존재 여부 확인
        myPageRepository.findByMemberId(member.getId())
            .orElseThrow(() -> new IllegalArgumentException("MyPage not found for this member."));

        // 재학 여부 업데이트
        if (request.getEnrollmentStatus() != null) {
            member.setEnrollmentStatus(request.getEnrollmentStatus());
        }

        // 비밀번호 업데이트 로직 (서비스 레이어에서 인코딩 처리)
        if (request.getPassword() != null) {
            member.changePassword(request.getPassword(), passwordEncoder);  // 인코딩된 비밀번호 저장
        }

        memberRepository.save(member);  // Member 업데이트 후 저장
    }
}
