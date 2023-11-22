package com.example.inhamonchallenge.domain.auth.exception;

import com.example.inhamonchallenge.global.error.BusinessException;
import com.example.inhamonchallenge.global.error.ErrorCode;

public class NotLoginException extends BusinessException {

    public NotLoginException() {
        super(ErrorCode.NOT_LOGIN_EXCEPTION);
    }
}
