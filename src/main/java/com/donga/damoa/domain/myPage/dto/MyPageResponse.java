package com.donga.damoa.domain.myPage.dto;

import com.donga.damoa.domain.member.domain.Major;
import com.donga.damoa.domain.member.dto.MajorDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyPageResponse {

    private String name;
    private String email;
    private List<MajorDto> majors;

    public static MyPageResponse from(String name, String email, List<Major> majors) {
        List<MajorDto> majorDtos = majors.stream()
            .map(major -> new MajorDto(major.getType().toString(), major.getName()))
            .collect(Collectors.toList());
        return new MyPageResponse(name, email, majorDtos);
    }
}
