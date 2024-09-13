package com.donga.damoa.domain.member.application;

import com.donga.damoa.domain.member.dao.MemberRepository;
import com.donga.damoa.domain.member.domain.Member;
import com.donga.damoa.domain.member.domain.MemberCreator;
import com.donga.damoa.domain.member.dto.SignUpRequest;
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
        Member member = memberCreator.createMember(request);
        memberRepository.save(member);
    }

}
