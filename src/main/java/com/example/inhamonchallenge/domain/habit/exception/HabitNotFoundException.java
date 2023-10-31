package com.example.inhamonchallenge.domain.habit.exception;

import com.example.inhamonchallenge.global.error.BusinessException;
import com.example.inhamonchallenge.global.error.ErrorCode;

public class HabitNotFoundException extends BusinessException {

    public HabitNotFoundException() {
        super(ErrorCode.NOT_FOUND_MEMBER_EXCEPTION);
    }
}
