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
public class SaveHabitResponse {

    private Long habitId;

    private String username;

    private Category category;

    private String title;

    private String content;

    private List<String> hashtag;

    private LocalDateTime createdAt;

    public static SaveHabitResponse from(Habit habit) {
        return SaveHabitResponse.builder()
                .habitId(habit.getId())
                .username(habit.getUser().getName())
                .category(habit.getCategory())
                .content(habit.getContent())
                .title(habit.getTitle())
                .hashtag(Arrays.asList(habit.getHashtags().split(",")))
                .createdAt(habit.getCreatedAt())
                .build();
    }
}
