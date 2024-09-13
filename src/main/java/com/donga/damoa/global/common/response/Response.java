package com.donga.damoa.global.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class Response<T> {

    private int code; // http 상태 코드
    private String message; // http 상태 메시지
    private T data; // 반환할 데이터

    public Response(HttpStatus httpStatus, String message, T data) {
        this.code = httpStatus.value();
        this.message = message;
        this.data = data;
    }

    public static <T> Response<T> of(HttpStatus httpStatus, String message, T data) {
        return new Response<>(httpStatus, message, data);
    }

    public static <T> Response<T> of(HttpStatus httpStatus, T data) {
        return Response.of(httpStatus, httpStatus.name(), data);
    }

    public static <T> Response<T> of(T data) {
        return Response.of(HttpStatus.OK, data);
    }

    public static Response<Void> of(HttpStatus httpStatus) {
        return Response.of(httpStatus, null);
    }

}
