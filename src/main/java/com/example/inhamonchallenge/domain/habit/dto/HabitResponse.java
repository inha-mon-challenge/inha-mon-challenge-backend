package com.example.inhamonchallenge.domain.habit.dto;

import com.example.inhamonchallenge.domain.habit.domain.Habit;
import com.example.inhamonchallenge.domain.model.Category;
import com.example.inhamonchallenge.domain.user.domain.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HabitResponse {

    private Long habitId;

    private String username;

    private Category category;

    private String content;

    private String image;

    private int totalRecordCnt;

    private int photoRecordCnt;

    private int currentRecordCnt;

    private List<String> hashtag;

    private int like;

    private LocalDateTime createdAt;

    public static HabitResponse from(Habit habit, List<String> hashtag) {
        return HabitResponse.builder()
                .habitId(habit.getId())
                .username(habit.getUser().getName())
                .category(habit.getCategory())
                .content(habit.getContent())
                .image(habit.getImage())
                .totalRecordCnt(habit.getTotalRecordCnt())
                .photoRecordCnt(habit.getPhotoRecordCnt())
                .currentRecordCnt(habit.getCurrentRecordCnt())
                .hashtag(hashtag)
                .like(habit.getLikeCnt())
                .createdAt(habit.getCreatedDateTime())
                .build();
    }

}
