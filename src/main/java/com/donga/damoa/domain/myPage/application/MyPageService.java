package com.donga.damoa.domain.myPage.application;

import com.donga.damoa.domain.myPage.dao.MyPageRepository;
import com.donga.damoa.domain.myPage.dto.MyPageRequest;
import com.donga.damoa.domain.myPage.dto.MyPageResponse;
import com.donga.damoa.domain.member.dao.MemberRepository;
import com.donga.damoa.domain.member.domain.Member;
import com.donga.damoa.domain.myPage.error.MyPageErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final MyPageRepository myPageRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public MyPageResponse getMyPage(Member member) {
        var myPage = myPageRepository.findByMemberId(member.getId())
            .orElseThrow(() -> new com.donga.damoa.domain.myPage.error.exception.MyPageNotFoundException(
                MyPageErrorCode.MY_PAGE_NOT_FOUND));

        return MyPageResponse.from(member.getName(), member.getEmail(), member.getMajors());
    }

    @Transactional
    public void updateMyPage(Member member, MyPageRequest request) {
        // 재학 여부 업데이트
        if (request.getEnrollmentStatus() != null) {
            member.setEnrollmentStatus(request.getEnrollmentStatus());
        }

        // 비밀번호 업데이트 로직 (서비스 레이어에서 인코딩 처리)
        if (request.getPassword() != null) {
            String encodedPassword = passwordEncoder.encode(request.getPassword());  // 패스워드 인코딩
            member.setPassword(encodedPassword);  // 인코딩된 비밀번호 저장
        }

        memberRepository.save(member);  // Member 업데이트 후 저장
    }

    @Transactional
    public void deactivateAccount(Member member) {
        member.deactivateAccount();  // 계정 상태를 비활성화로 변경
        memberRepository.save(member);  // 변경 사항 저장
    }
}
