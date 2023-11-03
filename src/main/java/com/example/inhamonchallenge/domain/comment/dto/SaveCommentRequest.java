package com.example.inhamonchallenge.domain.comment.dto;

import com.example.inhamonchallenge.domain.comment.domain.Comment;
import com.example.inhamonchallenge.domain.common.FeedType;
import com.example.inhamonchallenge.domain.habit.domain.Habit;
import com.example.inhamonchallenge.domain.record.domain.Record;
import com.example.inhamonchallenge.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaveCommentRequest {

    private String content;

    private FeedType feedType;

    private Long feedId;

    public static Comment toEntity(SaveCommentRequest request, User user, Habit habit, Record record) {
        return Comment.builder()
                .user(user)
                .habit(habit)
                .record(record)
                .content(request.getContent())
                .feedType(request.getFeedType())
                .reportCnt(0)
                .build();
    }
}
