package com.example.inhamonchallenge.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MethodArgumentNotValidExceptionResponse {
    private final int status;
    private final String message;

    public static MethodArgumentNotValidExceptionResponse from(String ex) {
        return new MethodArgumentNotValidExceptionResponse(400, ex);
    }

}
