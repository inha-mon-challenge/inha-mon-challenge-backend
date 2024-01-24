package com.example.inhamonchallenge.domain.follow.exception;

import com.example.inhamonchallenge.global.error.BusinessException;
import com.example.inhamonchallenge.global.error.ErrorCode;

public class ExistFollowException extends BusinessException {

    public ExistFollowException() {
        super(ErrorCode.EXIST_FOLLOW_EXCEPTION);
    }
}
