package com.example.inhamonchallenge.domain.follow.dto;

import com.example.inhamonchallenge.domain.user.domain.User;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FollowRequestResponse {

    private Long followId;
    private Long userId;
    private String name;
    private String profile;

    public static FollowRequestResponse from(Long followId, User user){
        return FollowRequestResponse.builder()
                .followId(followId)
                .userId(user.getId())
                .name(user.getName())
                .profile(user.getProfile())
                .build();
    }
}
