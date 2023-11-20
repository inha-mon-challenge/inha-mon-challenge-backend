package com.example.inhamonchallenge.domain.comment.dto;

import com.example.inhamonchallenge.domain.comment.domain.Comment;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveCommentResponse {

    private Long commentId;
    private Long userId;
    private String username;
    private String content;
    private LocalDateTime createdAt;

    public static SaveCommentResponse from(Comment comment) {
        return SaveCommentResponse.builder()
                .commentId(comment.getId())
                .userId(comment.getUser().getId())
                .username(comment.getUser().getName())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
