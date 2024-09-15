package com.donga.damoa.domain.member.application;

import com.donga.damoa.domain.member.domain.Member;

// 구현체 : IdPasswordLoginService
// 템플릿 메서드 패턴 적용
public abstract class AbstractLoginService {

    public final Member login(String email, String password) {
        Member member = findMemberByEmail(email);
        validateUserPassword(member, password);
        return member;
    }

    protected abstract Member findMemberByEmail(String email);

    protected abstract void validateUserPassword(Member member, String password);

}
