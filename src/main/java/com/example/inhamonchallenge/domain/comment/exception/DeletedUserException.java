package com.example.inhamonchallenge.domain.comment.exception;

import com.example.inhamonchallenge.global.error.BusinessException;
import com.example.inhamonchallenge.global.error.ErrorCode;

public class DeletedUserException extends BusinessException {

    public DeletedUserException() {
        super(ErrorCode.DELETED_USER_EXCEPTION);
    }
}
