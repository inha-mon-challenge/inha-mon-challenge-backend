package com.example.inhamonchallenge.domain.habit.dto;

import com.example.inhamonchallenge.domain.common.Category;
import com.example.inhamonchallenge.domain.habit.domain.Habit;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HabitResponse {

    private Long habitId;

    private String username;

    private Category category;

    private String title;

    private String content;

    private int totalRecordCnt;

    private int photoRecordCnt;

    private int currentRecordCnt;

    private List<String> hashtag;

    private int like;

    private LocalDateTime createdAt;

    public static HabitResponse from(Habit habit) {
        return HabitResponse.builder()
                .habitId(habit.getId())
                .username(habit.getUser().getName())
                .category(habit.getCategory())
                .content(habit.getContent())
                .title(habit.getTitle())
                .totalRecordCnt(habit.getTotalRecordCnt())
                .photoRecordCnt(habit.getPhotoRecordCnt())
                .currentRecordCnt(habit.getCurrentRecordCnt())
                .hashtag(Arrays.asList(habit.getHashtags().split(",")))
                .like(habit.getLikeCnt())
                .createdAt(habit.getCreatedDateTime())
                .build();
    }

}
