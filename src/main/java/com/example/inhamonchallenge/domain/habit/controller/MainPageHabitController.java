package com.example.inhamonchallenge.domain.habit.controller;

import com.example.inhamonchallenge.domain.common.dto.Result;
import com.example.inhamonchallenge.domain.habit.dto.RecommendHabitResponse;
import com.example.inhamonchallenge.domain.habit.service.MainPageHabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/habits/main")
@RequiredArgsConstructor
public class MainPageHabitController {

    private final MainPageHabitService mainPageHabitService;

    @GetMapping("/recommend")
    ResponseEntity<Result<List<RecommendHabitResponse>>> getRecommendHabits() {
        return ResponseEntity.ok(mainPageHabitService.getRecommendHabits());
    }
}
