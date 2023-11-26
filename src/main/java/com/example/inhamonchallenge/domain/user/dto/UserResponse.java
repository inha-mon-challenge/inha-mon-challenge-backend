package com.example.inhamonchallenge.domain.user.dto;

import com.example.inhamonchallenge.domain.common.Gender;
import com.example.inhamonchallenge.domain.user.domain.User;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserResponse {

    private Long userId;
    private String username;
    private String email;
    private LocalDate birth;
    private Gender gender;
    private String profile;

    public static UserResponse from(User user) {
        return UserResponse.builder()
                .userId(user.getId())
                .username(user.getName())
                .email(user.getEmail())
                .birth(user.getBirth())
                .gender(user.getGender())
                .profile(user.getProfile())
                .build();
    }
}
