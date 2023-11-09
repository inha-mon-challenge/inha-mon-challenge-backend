package com.example.inhamonchallenge.domain.auth.dto;

import com.example.inhamonchallenge.domain.user.domain.User;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignupResponse {
    private Long id;
    private String email;
    private String name;

    public static SignupResponse from(User user) {
        return SignupResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }
}
