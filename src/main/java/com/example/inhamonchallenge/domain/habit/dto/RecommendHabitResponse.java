package com.example.inhamonchallenge.domain.habit.dto;

import com.example.inhamonchallenge.domain.habit.domain.Habit;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecommendHabitResponse {

    private Long habitId;

    private Long userId;

    private String profile;

    private String title;

    public static RecommendHabitResponse from(Habit habit) {
        return RecommendHabitResponse.builder()
                .habitId(habit.getId())
                .userId(habit.getUser().getId())
                .profile(habit.getUser().getProfile())
                .title(habit.getTitle())
                .build();
    }
}
