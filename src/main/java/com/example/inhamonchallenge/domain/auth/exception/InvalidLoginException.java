package com.example.inhamonchallenge.domain.auth.exception;

import com.example.inhamonchallenge.global.error.BusinessException;
import com.example.inhamonchallenge.global.error.ErrorCode;

public class InvalidLoginException extends BusinessException {

    public InvalidLoginException() {
        super(ErrorCode.INVALID_LOGIN_EXCEPTION);
    }
}
