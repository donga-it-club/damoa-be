package com.donga.damoa.domain.member.application;

import com.donga.damoa.domain.member.dao.MemberRepository;
import com.donga.damoa.domain.member.domain.Member;
import com.donga.damoa.domain.member.domain.MemberCreator;
import com.donga.damoa.domain.member.dto.SignUpRequest;
import com.donga.damoa.domain.member.error.MemberErrorCode;
import com.donga.damoa.domain.member.error.exception.EmailDuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final MemberRepository memberRepository;
    private final MemberCreator memberCreator;

    @Transactional
    public void signUp(SignUpRequest request) {

        // 중복된 이메일로 회원가입을 할 수 없다.
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new EmailDuplicateException(MemberErrorCode.DUPLICATE_EMAIL);
        }

        Member member = memberCreator.createMember(request);
        memberRepository.save(member);
    }

}
