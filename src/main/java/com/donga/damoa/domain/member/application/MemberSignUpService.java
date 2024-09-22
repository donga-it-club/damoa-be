package com.donga.damoa.domain.member.application;

import com.donga.damoa.domain.member.dao.MemberRepository;
import com.donga.damoa.domain.member.domain.Member;
import com.donga.damoa.domain.member.domain.MemberCreator;
import com.donga.damoa.domain.member.dto.SignUpRequest;
import com.donga.damoa.domain.member.error.MemberErrorCode;
import com.donga.damoa.domain.member.error.exception.EmailDuplicateException;
import com.donga.damoa.domain.myPage.dao.MyPageRepository;
import com.donga.damoa.domain.myPage.domain.MyPage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberSignUpService {

    private final MemberRepository memberRepository;
    private final MyPageRepository myPageRepository;
    private final MemberCreator memberCreator;

    @Transactional
    public void signUp(SignUpRequest request) {

        // 중복된 이메일로 회원가입을 할 수 없다.
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new EmailDuplicateException(MemberErrorCode.DUPLICATE_EMAIL);
        }

        Member member = memberCreator.createMember(request);
        memberRepository.save(member);

        createMyPageForMember(member);
    }
    private void createMyPageForMember(Member member) {
        MyPage myPage = MyPage.builder()
            .member(member)
            .enrollmentStatus(member.getEnrollmentStatus()) // 회원의 재학 상태를 가져옴
            .build();
        myPageRepository.save(myPage);
    }

}
