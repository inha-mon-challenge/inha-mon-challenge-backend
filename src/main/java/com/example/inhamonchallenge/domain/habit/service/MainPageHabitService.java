package com.example.inhamonchallenge.domain.habit.service;

import com.example.inhamonchallenge.domain.common.dto.Result;
import com.example.inhamonchallenge.domain.habit.dto.RecommendHabitResponse;
import com.example.inhamonchallenge.domain.habit.repository.HabitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
}
