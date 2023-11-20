package com.example.inhamonchallenge.domain.auth.exception;

import com.example.inhamonchallenge.global.error.BusinessException;
import com.example.inhamonchallenge.global.error.ErrorCode;

public class InvalidRefreshTokenException extends BusinessException {

    public InvalidRefreshTokenException() {
        super(ErrorCode.INVALID_REFRESH_TOKEN_EXCEPTION);
    }
}
