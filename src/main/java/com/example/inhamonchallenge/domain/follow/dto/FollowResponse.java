package com.example.inhamonchallenge.domain.follow.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FollowResponse {

    private Long id;
    private Long followerId;
    private Long followingId;

    public static FollowResponse from(Long id, Long followerId, Long followingId){
        return FollowResponse.builder()
                .id(id)
                .followerId(followerId)
                .followingId(followingId)
                .build();
    }
}
