package com.example.inhamonchallenge.domain.auth.exception;

import com.example.inhamonchallenge.global.error.BusinessException;
import com.example.inhamonchallenge.global.error.ErrorCode;

public class WrongPasswordException extends BusinessException {

    public WrongPasswordException() {
        super(ErrorCode.WRONG_PASSWORD_EXCEPTION);
    }
}
