package com.donga.damoa.domain.project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateProject {

    @Schema(description = "제목", example = "프로젝트 제목")
    @NotBlank(message = "제목은 필수 입력 값입니다.")
    private String title;

    @Schema(description = "내용", example = "프로젝트 내용")
    @NotBlank(message = "내용은 필수 입력 값입니다.")
    private String content;

    @Schema(description = "링크", example = "https://server-technology.tistory.com")
    private String link;

}
