package com.example.inhamonchallenge.domain.comment.dto;

import com.example.inhamonchallenge.domain.comment.domain.Comment;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentResponse {

    private Long commentId;
    private String username;
    private String profile;
    private String content;
    private String createdAt;

    public static CommentResponse from(Comment comment) {
        return CommentResponse.builder()
                .commentId(comment.getId())
                .username(comment.getUser().getName())
                .profile(comment.getUser().getProfile())
                .content(comment.getContent())
                .createdAt(comment.getCreatedDateTime().toString())
                .build();
    }
}
