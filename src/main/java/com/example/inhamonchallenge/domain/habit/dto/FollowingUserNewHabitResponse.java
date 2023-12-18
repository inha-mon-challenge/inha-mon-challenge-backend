package com.example.inhamonchallenge.domain.habit.dto;

import com.example.inhamonchallenge.domain.habit.domain.Habit;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FollowingUserNewHabitResponse {

    private Long habitId;
    private String title;
    private Long userId;
    private String username;
    private String profile;

    public static FollowingUserNewHabitResponse from(Habit habit) {
        return FollowingUserNewHabitResponse.builder()
                .habitId(habit.getId())
                .title(habit.getTitle())
                .userId(habit.getUser().getId())
                .username(habit.getUser().getName())
                .profile(habit.getUser().getProfile())
                .build();
    }

}
