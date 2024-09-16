package com.donga.damoa.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MajorDto {

    @Schema(description = "전공 타입", example = "PRIMARY")
    private String majorType;

    @Schema(description = "전공 이름", example = "컴퓨터공학과")
    private String majorName;

}
