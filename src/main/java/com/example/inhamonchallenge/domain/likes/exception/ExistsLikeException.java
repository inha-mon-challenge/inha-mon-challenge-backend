package com.example.inhamonchallenge.domain.likes.exception;

import com.example.inhamonchallenge.global.error.BusinessException;
import com.example.inhamonchallenge.global.error.ErrorCode;

public class ExistsLikeException extends BusinessException {

    public ExistsLikeException() {
        super(ErrorCode.ExistsLikeException);
    }
}
