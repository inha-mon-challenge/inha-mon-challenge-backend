package com.example.inhamonchallenge.domain.user.exception;

import com.example.inhamonchallenge.global.error.BusinessException;
import com.example.inhamonchallenge.global.error.ErrorCode;

public class InvalidUsernameException extends BusinessException {

    public InvalidUsernameException() {
        super(ErrorCode.INVALID_USERNAME_EXCEPTION);
    }
}
