package com.example.inhamonchallenge.domain.mail.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmailVerifyResponse {

    private boolean isVerified;
    private String message;

    public static EmailVerifyResponse from(boolean isVerified, String message) {
        EmailVerifyResponse response = new EmailVerifyResponse();
        response.isVerified = isVerified;
        response.message = message;
        return response;
    }
}
