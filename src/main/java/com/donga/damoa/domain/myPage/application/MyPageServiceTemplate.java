package com.donga.damoa.domain.myPage.application;

import com.donga.damoa.domain.member.domain.Member;
import com.donga.damoa.domain.myPage.dto.MyPageRequest;
import com.donga.damoa.domain.myPage.dto.MyPageResponse;

public abstract class MyPageServiceTemplate {

    //마이페이지 정보 수정
    public final void updateMyPage(Member member, MyPageRequest request) {
        validateMember(member);
        processUpdate(member, request);
    }

    //마이페이지 정보 조회
    public final MyPageResponse getMyPage(Member member) {
        validateMember(member);
        return processGetMyPage(member);
    }

    //계정 비활성화
    public final void deactivateAccount(Member member) {
        validateMember(member);
        processDeactivate(member);
    }

    protected abstract void validateMember(Member member);

    protected abstract void processUpdate(Member member, MyPageRequest request);

    protected abstract MyPageResponse processGetMyPage(Member member);

    protected abstract void processDeactivate(Member member);
}
