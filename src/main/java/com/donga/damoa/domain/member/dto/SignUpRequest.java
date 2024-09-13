package com.donga.damoa.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "이메일", example = "test@test.com")
    @Email(message = "이메일 형식에 맞게 입력해주세요.")
    @NotBlank(message = "이메일은 필수 입력값입니다.")
    private String email; // 이메일

    @Schema(description = "비밀번호", example = "test")
    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    private String password; // 비밀번호

    @Schema(description = "이름", example = "홍길동")
    @NotBlank(message = "이름은 필수 입력값입니다.")
    private String memberName; // 이름

    @Schema(description = "주전공", example = "컴퓨터공학과")
    @NotBlank(message = "주전공은 필수 입력값입니다.")
    private String majorName; // 주전공

    @Schema(description = "부전공", example = "경영정보학과")
    private String minorName; // 부전공

    @Schema(description = "재학 여부", example = "ENROLLED")
    @NotBlank(message = "재학 여부는 필수 입력값입니다.")
    private String enrollmentStatus; // 재학 여부

    @Schema(description = "PR 링크", example = "https://server-technology.tistory.com")
    private String prLink;

}
