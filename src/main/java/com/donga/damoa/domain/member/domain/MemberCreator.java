package com.donga.damoa.domain.member.domain;

import com.donga.damoa.domain.member.dto.SignUpRequest;
import io.micrometer.common.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberCreator {

    private final PasswordEncoder passwordEncoder;

    public Member createMember(SignUpRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        List<Major> majors = createMajors(request.getMajorName(), request.getMinorName());
        return Member.builder()
            .majors(majors)
            .email(request.getEmail())
            .password(encodedPassword)
            .name(request.getMemberName())
            .prLink(request.getPrLink())
            .enrollmentStatus(EnrollmentStatus.valueOf(request.getEnrollmentStatus()))
            .build();
    }

    public List<Major> createMajors(String majorName, String minorName) {
        List<Major> majors = new ArrayList<>();
        // majorName -> 주 전공
        majors.add(Major.builder()
            .type(MajorType.PRIMARY)
            .name(majorName)
            .build());

        // if(minorName != null) -> 복수 전공
        if (StringUtils.isNotEmpty(minorName)) {
            majors.add(Major.builder()
                .type(MajorType.SECONDARY)
                .name(minorName)
                .build());
        }

        return majors;
    }

}
