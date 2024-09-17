package com.donga.damoa.domain.myPage.application;

import com.donga.damoa.domain.member.domain.Member;
import com.donga.damoa.domain.myPage.dto.MyPageRequest;
import com.donga.damoa.domain.myPage.dto.MyPageResponse;

public abstract class MyPageServiceTemplate {

    public final void updateMyPage(Member member, MyPageRequest request) {
        validateMember(member);
        processUpdate(member, request);
    }

    public final MyPageResponse getMyPage(Member member) {
        validateMember(member);
        return processGetMyPage(member);
    }

    public final void deactivateAccount(Member member) {
        validateMember(member);
        processDeactivate(member);
    }

    protected abstract void validateMember(Member member);

    protected abstract void processUpdate(Member member, MyPageRequest request);

    protected abstract MyPageResponse processGetMyPage(Member member);

    protected abstract void processDeactivate(Member member);
}
