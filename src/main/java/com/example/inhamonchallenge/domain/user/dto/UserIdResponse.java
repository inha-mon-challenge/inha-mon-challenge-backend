package com.example.inhamonchallenge.domain.user.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserIdResponse {

    private Long userId;

    @Builder
    public UserIdResponse(Long userId) {
        this.userId = userId;
    }

    public static UserIdResponse from(Long userId) {
        return UserIdResponse.builder()
                .userId(userId)
                .build();
    }
}
