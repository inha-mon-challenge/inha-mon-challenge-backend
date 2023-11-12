package com.example.inhamonchallenge.domain.follow.dto;

import com.example.inhamonchallenge.domain.user.domain.User;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FollowingUserResponse {

    private Long id;
    private String name;
    private String profile;

    public static FollowingUserResponse from(User user){
        return FollowingUserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .profile(user.getProfile())
                .build();
    }
}
