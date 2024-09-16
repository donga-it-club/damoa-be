package com.donga.damoa.domain.member.application;

import com.donga.damoa.domain.member.domain.Member;

// Template Method 패턴
public abstract class AuthLoginService {

    public final String login(String email, String password) {
        Member member = findMemberByEmail(email);
        validateUserPassword(member, password);
        return generateToken(member);
    }

    protected abstract Member findMemberByEmail(String email);

    protected abstract void validateUserPassword(Member member, String password);

    protected abstract String generateToken(Member member);
}
