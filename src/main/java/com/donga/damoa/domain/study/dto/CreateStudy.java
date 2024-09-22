package com.donga.damoa.domain.study.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateStudy {

    @Schema(description = "게시물 제목", example = "커뮤니티 토론 앱 백엔드 개발자를 구합니다.")
    @NotBlank(message = "게시물 제목은 필수 입력 값입니다.")
    private String title;

    @Schema(description = "게시물 내용", example = "주요 기능은 실시간 채팅 기능과 웹사이트 크롤링 기능이고 ..")
    @NotBlank(message = "게시물 내용은 필수 입력 값입니다.")
    private String content;

    @Schema(description = "신청 링크", example = "https://server-technology.tistory.com/")
    private String link;

    @Schema(description = "모집 마감일", example = "2024-09-22T23:59:59")
    @NotNull(message = "모집 마감일은 필수 입력 값입니다.")
    private LocalDateTime recruitDeadline;

}
