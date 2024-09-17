package com.donga.damoa.domain.myPage.error;

import com.donga.damoa.global.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MyPageErrorCode implements ErrorCode {

    MY_PAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "MyPage not found."),
    INVALID_ENROLLMENT_STATUS(HttpStatus.BAD_REQUEST, "Invalid enrollment status."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "Member not found."),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
