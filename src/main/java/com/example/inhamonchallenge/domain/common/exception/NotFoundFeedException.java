package com.example.inhamonchallenge.domain.common.exception;

import com.example.inhamonchallenge.global.error.BusinessException;
import com.example.inhamonchallenge.global.error.ErrorCode;

public class NotFoundFeedException extends BusinessException {

    public NotFoundFeedException() {
        super(ErrorCode.NOT_FOUND_FEED_EXCEPTION);
    }
}
