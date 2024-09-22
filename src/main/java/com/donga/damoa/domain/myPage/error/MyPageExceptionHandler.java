package com.donga.damoa.domain.myPage.error;

import com.donga.damoa.domain.myPage.api.MyPageController;
import com.donga.damoa.domain.myPage.error.exception.MyPageNotFoundException;
import com.donga.damoa.global.error.ErrorCode;
import com.donga.damoa.global.error.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = {MyPageController.class})
public class MyPageExceptionHandler {

    @ExceptionHandler(MyPageNotFoundException.class)
    public ResponseEntity<Object> handleMyPageNotFoundException(MyPageNotFoundException e) {
        ErrorCode errorCode = e.getErrorCode();
        return handleException(errorCode);
    }

    private ResponseEntity<Object> handleException(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
            .body(createErrorResponse(errorCode));
    }

    private ErrorResponse createErrorResponse(ErrorCode errorCode) {
        return new ErrorResponse(errorCode);
    }
}
