package com.example.inhamonchallenge.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 습관
    NOT_FOUND_MEMBER_EXCEPTION(404, "존재하지 않는 습관입니다.", NOT_FOUND),
    //습관기록
    NOT_FOUND_RECORD_EXCEPTION(404, "존재하지 않는 습관 기록입니다.", NOT_FOUND),
    //유저
    NOT_FOUND_USER_EXCEPTION(404, "존재하지 않는 사용자입니다.", NOT_FOUND),
    EXIST_EMAIL_EXCEPTION(409, "이미 존재하는 이메일입니다.", HttpStatus.CONFLICT),
    //댓글
    NOT_FOUND_COMMENT_EXCEPTION(404, "존재하지 않는 댓글입니다.", NOT_FOUND),
    //종아요
    ExistsLikeException(409, "이미 좋아요를 누른 피드입니다.", HttpStatus.CONFLICT),
    NOT_FOUND_LIKES_EXCEPTION(404, "존재하지 않는 좋아요입니다.", NOT_FOUND),
    //공통
    UPDATE_DENIED_EXCEPTION(403, "수정 권한이 없습니다.", FORBIDDEN),
    DELETE_DENIED_EXCEPTION(403, "삭제 권한이 없습니다.", FORBIDDEN),
    NOT_FOUND_FEED_EXCEPTION(404, "존재하지 않는 피드입니다.", NOT_FOUND);
    private final int status;
    private final String message;
    private final HttpStatus httpStatus;
}
