package com.donga.damoa.domain.member.dto;

import com.donga.damoa.domain.member.domain.EnrollmentStatus;
import com.donga.damoa.domain.member.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MyInfoResponse {

    @Schema(description = "이메일", example = "test@test.com")
    private final String email;

    @Schema(description = "이름", example = "홍길동")
    private final String name;

    @Schema(description = "전공")
    private final List<MajorDto> majors;

    @Schema(description = "등록 상태", example = "ENROLLED")
    private final EnrollmentStatus enrollmentStatus;

    @Schema(description = "PR 링크", example = "https://server-technology.tistory.com")
    private final String prLink;

    @Schema(description = "권한", example = "ROLE_USER")
    private final String role;

    public static MyInfoResponse of(Member member) {
        return new MyInfoResponse(
            member.getEmail(),
            member.getName(),
            member.getMajors().stream()
                .map(major -> new MajorDto(major.getType().toString(), major.getName()))
                .toList(),
            member.getEnrollmentStatus(),
            member.getPrLink(),
            member.getRole().getKey()
        );
    }

}
