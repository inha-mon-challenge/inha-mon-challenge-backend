package com.example.inhamonchallenge.domain.comment.exception;

import com.example.inhamonchallenge.global.error.BusinessException;
import com.example.inhamonchallenge.global.error.ErrorCode;

public class NotFoundCommentException extends BusinessException {

    public NotFoundCommentException() {
        super(ErrorCode.NOT_FOUND_COMMENT_EXCEPTION);
    }
}
