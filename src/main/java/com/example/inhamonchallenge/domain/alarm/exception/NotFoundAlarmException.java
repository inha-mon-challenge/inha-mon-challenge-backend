package com.example.inhamonchallenge.domain.alarm.exception;

import com.example.inhamonchallenge.global.error.BusinessException;
import com.example.inhamonchallenge.global.error.ErrorCode;

public class NotFoundAlarmException extends BusinessException {

    public NotFoundAlarmException() {
        super(ErrorCode.NOT_FOUND_ALARM_EXCEPTION);
    }
}
