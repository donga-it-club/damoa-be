package com.donga.damoa.domain.member.error;

import com.donga.damoa.domain.member.api.MemberController;
import com.donga.damoa.domain.member.error.exception.EmailDuplicateException;
import com.donga.damoa.global.error.ErrorCode;
import com.donga.damoa.global.error.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = {MemberController.class})
public class MemberExceptionHandler {

    @ExceptionHandler(EmailDuplicateException.class)
    public ResponseEntity<Object> handleEmailDuplicateException(EmailDuplicateException e) {
        ErrorCode errorCode = e.getErrorCode();
        return handleException(errorCode);
    }

    private ResponseEntity<Object> handleException(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
            .body(createErrorResponse(errorCode));
    }

    private ErrorResponse createErrorResponse(ErrorCode errorCode) {
        return ErrorResponse.builder()
            .code(errorCode.name())
            .message(errorCode.getMessage())
            .build();
    }
}
