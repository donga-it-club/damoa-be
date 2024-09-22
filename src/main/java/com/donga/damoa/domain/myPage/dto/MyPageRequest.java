package com.donga.damoa.domain.myPage.dto;

import com.donga.damoa.domain.member.domain.EnrollmentStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MyPageRequest {

    private EnrollmentStatus enrollmentStatus;
    private String password;
}
