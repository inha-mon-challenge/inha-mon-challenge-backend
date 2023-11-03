package com.example.inhamonchallenge.domain.likes.dto;

import com.example.inhamonchallenge.domain.common.FeedType;
import com.example.inhamonchallenge.domain.likes.domain.Likes;
import com.example.inhamonchallenge.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LikesRequest {

    private Long feedId;
    private FeedType feedType;

    public static Likes toEntity(LikesRequest request, User user) {
        return Likes.builder()
                .user(user)
                .feedId(request.getFeedId())
                .feedType(request.getFeedType())
                .build();
    }
}
