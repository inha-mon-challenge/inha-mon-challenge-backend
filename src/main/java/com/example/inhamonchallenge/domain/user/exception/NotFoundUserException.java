package com.example.inhamonchallenge.domain.user.exception;

import com.example.inhamonchallenge.global.error.BusinessException;
import com.example.inhamonchallenge.global.error.ErrorCode;

public class NotFoundUserException extends BusinessException {

    public NotFoundUserException() {
        super(ErrorCode.NOT_FOUND_USER_EXCEPTION);
    }
}
