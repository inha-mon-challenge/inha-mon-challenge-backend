package com.example.inhamonchallenge.domain.common.exception;

import com.example.inhamonchallenge.global.error.BusinessException;
import com.example.inhamonchallenge.global.error.ErrorCode;

public class DeleteDeniedException extends BusinessException {

    public DeleteDeniedException() {
        super(ErrorCode.DELETE_DENIED_EXCEPTION);
    }
}
