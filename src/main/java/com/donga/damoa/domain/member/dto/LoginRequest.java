package com.donga.damoa.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginRequest {

    @Schema(description = "이메일", example = "test@test.com")
    @Email(message = "이메일 형식에 맞게 입력하세요.")
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String email;

    @Schema(description = "비밀번호", example = "test")
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;

}
