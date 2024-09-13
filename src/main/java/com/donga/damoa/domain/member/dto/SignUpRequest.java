package com.donga.damoa.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SignUpRequest {

    @Email(message = "이메일 형식에 맞게 입력해주세요.")
    @NotBlank(message = "이메일은 필수 입력값입니다.")
    private String email; // 이메일

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    private String password; // 비밀번호

    @NotBlank(message = "이름은 필수 입력값입니다.")
    private String memberName; // 이름

    @NotBlank(message = "주전공은 필수 입력값입니다.")
    private String majorName; // 주전공

    private String minorName; // 부전공

    @NotBlank(message = "재학 여부는 필수 입력값입니다.")
    private String enrollmentStatus; // 재학 여부

    private String prLink;

}
