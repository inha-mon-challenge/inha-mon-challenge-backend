package com.example.inhamonchallenge.domain.auth.exception;

import com.example.inhamonchallenge.global.error.BusinessException;
import com.example.inhamonchallenge.global.error.ErrorCode;

public class WrongEmailException extends BusinessException {

    public WrongEmailException() {
        super(ErrorCode.WRONG_EMAIL_EXCEPTION);
    }
}
