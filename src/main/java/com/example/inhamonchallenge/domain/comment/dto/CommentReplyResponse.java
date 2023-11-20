package com.example.inhamonchallenge.domain.comment.dto;

import com.example.inhamonchallenge.domain.comment.domain.Comment;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentReplyResponse {

    private Long commentId;
    private String username;
    private String profile;
    private String content;
    private LocalDateTime createdAt;

    public static CommentReplyResponse from(Comment comment) {
        return CommentReplyResponse.builder()
                .commentId(comment.getId())
                .username(comment.getUser().getName())
                .profile(comment.getUser().getProfile())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .build();
    }

}
