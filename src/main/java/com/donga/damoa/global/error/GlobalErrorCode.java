package com.donga.damoa.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GlobalErrorCode implements ErrorCode {

    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "적절하지 않은 입력 값입니다."),
    FORBIDDEN_ACCESS(HttpStatus.FORBIDDEN, "접근 권한이 없습니다."),
    UNAUTHORIZED_ACCESS(HttpStatus.UNAUTHORIZED, "인증이 필요합니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;

}
