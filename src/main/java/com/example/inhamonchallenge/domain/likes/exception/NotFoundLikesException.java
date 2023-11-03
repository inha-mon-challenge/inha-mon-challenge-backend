package com.example.inhamonchallenge.domain.likes.exception;

import com.example.inhamonchallenge.global.error.BusinessException;
import com.example.inhamonchallenge.global.error.ErrorCode;

public class NotFoundLikesException extends BusinessException {

    public NotFoundLikesException() {
        super(ErrorCode.NOT_FOUND_LIKES_EXCEPTION);
    }
}
