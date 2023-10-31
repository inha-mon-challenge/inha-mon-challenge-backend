package com.example.inhamonchallenge.domain.common.exception;

import com.example.inhamonchallenge.global.error.BusinessException;
import com.example.inhamonchallenge.global.error.ErrorCode;

public class UpdateDeniedException extends BusinessException {

    public UpdateDeniedException() {
        super(ErrorCode.UPDATE_DENIED_EXCEPTION);
    }
}
