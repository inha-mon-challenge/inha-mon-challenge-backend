package com.example.inhamonchallenge.domain.auth.exception;

import com.example.inhamonchallenge.global.error.BusinessException;
import com.example.inhamonchallenge.global.error.ErrorCode;

public class LogoutUserException extends BusinessException {

    public LogoutUserException() {
        super(ErrorCode.LOGOUT_USER_EXCEPTION);
    }
}
