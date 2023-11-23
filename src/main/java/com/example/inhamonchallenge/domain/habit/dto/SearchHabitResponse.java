package com.example.inhamonchallenge.domain.habit.dto;

import com.example.inhamonchallenge.domain.common.Category;
import com.example.inhamonchallenge.domain.habit.domain.Habit;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchHabitResponse {

    private Long habitId;

    private Long userId;

    private String username;

    private String profile;

    private Category category;

    private String title;

    public static SearchHabitResponse from(Habit habit) {
        return SearchHabitResponse.builder()
                .habitId(habit.getId())
                .userId(habit.getUser().getId())
                .username(habit.getUser().getName())
                .profile(habit.getUser().getProfile())
                .category(habit.getCategory())
                .title(habit.getTitle())
                .build();
    }

}
