package com.donga.damoa.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GlobalErrorCode implements ErrorCode {

    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "적절하지 않은 입력 값입니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;

}
