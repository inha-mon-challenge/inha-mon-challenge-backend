package com.example.inhamonchallenge.domain.comment.dto;

import com.example.inhamonchallenge.domain.comment.domain.Comment;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentWithReplyResponse {

    private Long commentId;
    private String username;
    private String profile;
    private String content;
    private LocalDateTime createdAt;
    List<CommentReplyResponse> replies;

    public static CommentWithReplyResponse from(Comment comment, List<CommentReplyResponse> replies) {
        return CommentWithReplyResponse.builder()
                .commentId(comment.getId())
                .username(comment.getUser().getName())
                .profile(comment.getUser().getProfile())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .replies(replies)
                .build();
    }
}
