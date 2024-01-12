package com.example.inhamonchallenge.domain.mail.exception;

import com.example.inhamonchallenge.global.error.BusinessException;
import com.example.inhamonchallenge.global.error.ErrorCode;

public class NotFoundAuthException extends BusinessException {

    public NotFoundAuthException() {
        super(ErrorCode.NOT_FOUND_AUTH_EXCEPTION);
    }
}
