package com.donga.damoa.global.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorCode errorCode = GlobalErrorCode.INVALID_INPUT_VALUE;
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
