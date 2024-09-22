package com.donga.damoa.domain.myPage.error.exception;

import com.donga.damoa.domain.myPage.error.MyPageErrorCode;
import lombok.Getter;

@Getter
public class MyPageNotFoundException extends RuntimeException {

    private final MyPageErrorCode errorCode;

    public MyPageNotFoundException(MyPageErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
