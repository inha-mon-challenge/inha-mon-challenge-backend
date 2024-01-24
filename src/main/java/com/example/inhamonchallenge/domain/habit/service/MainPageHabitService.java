package com.example.inhamonchallenge.domain.habit.service;

import com.example.inhamonchallenge.domain.common.dto.Result;
import com.example.inhamonchallenge.domain.habit.domain.Habit;
import com.example.inhamonchallenge.domain.habit.dto.FollowingUserNewHabitResponse;
import com.example.inhamonchallenge.domain.habit.dto.RecommendHabitResponse;
import com.example.inhamonchallenge.domain.habit.repository.HabitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.inhamonchallenge.global.security.SecurityUtil.getCurrentMemberId;

@Service
@Transactional
@RequiredArgsConstructor
public class MainPageHabitService {

    private final HabitRepository habitRepository;

    public Result<List<RecommendHabitResponse>> getRecommendHabits() {
        List<RecommendHabitResponse> recommendHabits = habitRepository.findRandomHabits()
                .stream()
                .map(RecommendHabitResponse::from)
                .collect(Collectors.toList());

        return new Result<>(recommendHabits);
    }

    public Result<List<FollowingUserNewHabitResponse>> getFollowingNewHabit(Long cursor, boolean isLoggedIn) {
        if (cursor == null) {
            cursor = Long.MAX_VALUE;
        }
        List<Habit> followingUserNewHabits;
        LocalDateTime oneDayAgo = LocalDateTime.now().minusDays(1);
        if (isLoggedIn) {
            followingUserNewHabits = habitRepository.findFollowingNewHabit(cursor, getCurrentMemberId(), oneDayAgo, Pageable.ofSize(1)).getContent();
        } else {
            followingUserNewHabits = habitRepository.findPublicNewHabit(cursor, oneDayAgo, Pageable.ofSize(1)).getContent();
        }
        return new Result<>(followingUserNewHabits.stream()
                .map(FollowingUserNewHabitResponse::from)
                .collect(Collectors.toList()));
    }
}
