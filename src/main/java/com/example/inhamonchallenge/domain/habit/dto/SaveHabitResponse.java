package com.example.inhamonchallenge.domain.habit.dto;

import com.example.inhamonchallenge.domain.habit.domain.Habit;
import com.example.inhamonchallenge.domain.model.Category;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveHabitResponse {

    private Long habitId;

    private String username;

    private Category category;

    private String content;

    private String image;

    private LocalDateTime createdAt;

    public static SaveHabitResponse from(Habit habit) {
        return SaveHabitResponse.builder()
                .habitId(habit.getId())
                .username(habit.getUser().getName())
                .category(habit.getCategory())
                .content(habit.getContent())
                .image(habit.getImage())
                .createdAt(habit.getCreatedDateTime())
                .build();
    }
}
