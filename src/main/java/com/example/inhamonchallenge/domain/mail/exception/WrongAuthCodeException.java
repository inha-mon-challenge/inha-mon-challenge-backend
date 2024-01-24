package com.example.inhamonchallenge.domain.mail.exception;

import com.example.inhamonchallenge.global.error.BusinessException;
import com.example.inhamonchallenge.global.error.ErrorCode;

public class WrongAuthCodeException extends BusinessException {

    public WrongAuthCodeException() {
        super(ErrorCode.WRONG_AUTH_CODE_EXCEPTION);
    }
}
