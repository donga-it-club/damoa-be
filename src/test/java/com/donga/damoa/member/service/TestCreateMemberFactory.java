package com.donga.damoa.member.service;

import com.donga.damoa.domain.member.domain.EnrollmentStatus;
import com.donga.damoa.domain.member.domain.Member;

public class TestCreateMemberFactory {

    public static Member createMember(String email, String encodedPassword) {
        return Member.builder()
            .email(email)
            .password(encodedPassword)
            .name("test")
            .enrollmentStatus(EnrollmentStatus.ENROLLED)
            .prLink(null)
            .build();
    }

}
