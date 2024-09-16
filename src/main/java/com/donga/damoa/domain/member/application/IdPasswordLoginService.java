package com.donga.damoa.domain.member.application;

import com.donga.damoa.domain.member.dao.MemberRepository;
import com.donga.damoa.domain.member.domain.Member;
import com.donga.damoa.domain.member.error.MemberErrorCode;
import com.donga.damoa.domain.member.error.exception.EmailNotFoundException;
import com.donga.damoa.domain.member.error.exception.InvalidPasswordException;
import com.donga.damoa.global.config.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IdPasswordLoginService extends AuthLoginService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final JwtTokenProvider tokenProvider;

    @Override
    protected Member findMemberByEmail(String email) {
        // 이메일로 사용자를 조회하였을 때, 존재하지 않는다면 예외가 발생한다.
        return memberRepository.findMemberByEmail(email)
            .orElseThrow(() -> new EmailNotFoundException(MemberErrorCode.EMAIL_NOT_FOUND));
    }

    @Override
    protected void validateUserPassword(Member member, String password) {
        // 비밀번호가 일치하지 않는다면 예외가 발생한다.
        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new InvalidPasswordException(MemberErrorCode.INVALID_PASSWORD);
        }
    }

    @Override
    protected String generateToken(Member member) {
        return tokenProvider.createJwtToken(member);
    }
}
