package com.example.inhamonchallenge.domain.user.exception;

import com.example.inhamonchallenge.global.error.BusinessException;
import com.example.inhamonchallenge.global.error.ErrorCode;

public class ExistUsernameException extends BusinessException {

    public ExistUsernameException() {
        super(ErrorCode.EXIST_USERNAME_EXCEPTION);
    }
}
