package com.example.inhamonchallenge.domain.record.exception;

import com.example.inhamonchallenge.global.error.BusinessException;
import com.example.inhamonchallenge.global.error.ErrorCode;

public class NotFoundRecordException extends BusinessException {

    public NotFoundRecordException() {
        super(ErrorCode.NOT_FOUND_RECORD_EXCEPTION);
    }
}
