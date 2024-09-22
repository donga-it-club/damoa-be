package com.donga.damoa.myPage.service;

import com.donga.damoa.domain.myPage.dto.MyPageRequest;
import com.donga.damoa.domain.member.domain.EnrollmentStatus;

public class TestMyPageRequestFactory {

    public static MyPageRequest createMyPageRequest() {
        return new MyPageRequest(
            EnrollmentStatus.ENROLLED,        // EnrollmentStatus enum 값 사용
            "newPassword"                  // 변경할 패스워드
        );
    }
}
