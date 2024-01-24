package com.example.inhamonchallenge.domain.mail.exception;

import com.example.inhamonchallenge.global.error.BusinessException;
import com.example.inhamonchallenge.global.error.ErrorCode;

public class AuthTimeOutException extends BusinessException {

    public AuthTimeOutException() {
        super(ErrorCode.AUTH_TIME_OUT_EXCEPTION);
    }
}
