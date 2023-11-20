package com.example.inhamonchallenge.domain.common.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageResponse {

    private String message;

    public static MessageResponse from(String message) {
        MessageResponse response = new MessageResponse();
        response.message = message;
        return response;
    }
}
