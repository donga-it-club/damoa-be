package com.donga.damoa.domain.member.application;

import com.donga.damoa.domain.member.dao.MemberRepository;
import com.donga.damoa.domain.member.domain.Member;
import com.donga.damoa.domain.member.dto.MyInfoResponse;
import com.donga.damoa.domain.member.error.MemberErrorCode;
import com.donga.damoa.domain.member.error.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberQueryService {

    private final MemberRepository memberRepository;

    public MyInfoResponse getMyInfo(Member member) {
        Member memberWithMajors = memberRepository.findByIdWithMajor(member.getId())
            .orElseThrow(() -> new UserNotFoundException(MemberErrorCode.USER_NOT_FOUND));
        return MyInfoResponse.of(memberWithMajors);
    }

}
