package com.example.inhamonchallenge.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 습관
    NOT_FOUND_MEMBER_EXCEPTION(404, "습관이 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    //유저
    NOT_FOUND_USER_EXCEPTION(404, "사용자가 존재하지 않습니다.", HttpStatus.NOT_FOUND);

    private final int status;
    private final String message;
    private final HttpStatus httpStatus;
}
