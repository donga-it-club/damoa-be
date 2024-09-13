package com.donga.damoa.member.service;

import com.donga.damoa.domain.member.dto.SignUpRequest;

public class TestSignUpRequestFactory {

    public static SignUpRequest createSignUpWithEmail(String email) {
        SignUpRequest request = new SignUpRequest(
            email,
            "password",
            "memberName",
            "majorName",
            "minorName",
            "enrollmentStatus",
            "prLink"
        );

        return request;
    }

}
