package com.example.inhamonchallenge.domain.habit.controller;

import com.example.inhamonchallenge.domain.common.dto.Result;
import com.example.inhamonchallenge.domain.habit.dto.FollowingUserNewHabitResponse;
import com.example.inhamonchallenge.domain.habit.dto.RecommendHabitResponse;
import com.example.inhamonchallenge.domain.habit.service.MainPageHabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    //팔로워가 새로 시작한 습관 보여주기
    @GetMapping("/following/new-habits")
    ResponseEntity<Result<List<FollowingUserNewHabitResponse>>> getFollowingNewHabit(@RequestParam(required = false) Long cursor,
                                              @AuthenticationPrincipal UserDetails userDetails) {
        boolean isLoggedIn = userDetails != null;
        return ResponseEntity.ok(mainPageHabitService.getFollowingNewHabit(cursor, isLoggedIn));
    }
}
