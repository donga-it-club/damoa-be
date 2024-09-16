package com.donga.damoa.domain.member.error.exception;

import com.donga.damoa.global.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EmailNotFoundException extends RuntimeException{

    private final ErrorCode errorCode;

}
