package com.example.inhamonchallenge.domain.user.exception;

import com.example.inhamonchallenge.global.error.BusinessException;
import com.example.inhamonchallenge.global.error.ErrorCode;

public class ExistEmailException extends BusinessException {

    public ExistEmailException() {
        super(ErrorCode.EXIST_EMAIL_EXCEPTION);
    }
}
