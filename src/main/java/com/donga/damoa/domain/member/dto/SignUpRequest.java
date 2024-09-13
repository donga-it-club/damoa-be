package com.donga.damoa.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignUpRequest {

    @Email(message = "이메일 형식에 맞게 입력해주세요.")
    private String email; // 이메일

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password; // 비밀번호

    @NotEmpty(message = "이름을 입력해주세요.")
    private String memberName; // 이름

    @NotEmpty(message = "주전공을 입력해주세요.")
    private String majorName; // 주전공

    private String minorName; // 부전공

    @NotEmpty(message = "재학 여부를 입력해주세요.")
    private String enrollmentStatus; // 재학 여부

    private String prLink;

}
