package com.example.inhamonchallenge.domain.follow.exception;

import com.example.inhamonchallenge.global.error.BusinessException;
import com.example.inhamonchallenge.global.error.ErrorCode;

public class NotFoundFollowException extends BusinessException {

    public NotFoundFollowException() {
        super(ErrorCode.NOT_FOUND_FOLLOW_EXCEPTION);
    }
}
