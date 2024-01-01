package com.example.inhamonchallenge.domain.habit.controller;

import com.example.inhamonchallenge.domain.common.PrivacySetting;
import com.example.inhamonchallenge.domain.common.dto.Result;
import com.example.inhamonchallenge.domain.habit.dto.HabitAndRecordResponse;
import com.example.inhamonchallenge.domain.habit.dto.HabitResponse;
import com.example.inhamonchallenge.domain.habit.dto.SaveHabitRequest;
import com.example.inhamonchallenge.domain.habit.dto.SaveHabitResponse;
import com.example.inhamonchallenge.domain.habit.service.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;

    @GetMapping("/habits/{habitId}")
    ResponseEntity<HabitAndRecordResponse> habitDetails(@PathVariable Long habitId) {
        return ResponseEntity.ok(habitService.getHabit(habitId));
    }

    @GetMapping("/users/{userId}/habits")
    ResponseEntity<Result<List<HabitAndRecordResponse>>> habitList(@PathVariable Long userId,
                                                                   @AuthenticationPrincipal UserDetails userDetails) {
        boolean isLoggedIn = userDetails != null;
        Result<List<HabitAndRecordResponse>> response = habitService.getAllHabitsAndRecordsByUserId(userId, isLoggedIn);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/habits")
    ResponseEntity<SaveHabitResponse> habitAdd(@ModelAttribute SaveHabitRequest request) {
        SaveHabitResponse response = habitService.addHabit(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/habits/{habitId}")
    ResponseEntity<SaveHabitResponse> habitUpdate(@ModelAttribute SaveHabitRequest request, @PathVariable Long habitId) {
        SaveHabitResponse response = habitService.updateHabit(request, habitId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/habits/{habitId}/privacy")
    ResponseEntity<Void> habitPrivacyChange(@PathVariable Long habitId, @RequestParam PrivacySetting privacy) {
        habitService.changeHabitPrivacy(habitId, privacy);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/habits/{habitId}")
    ResponseEntity<Void> habitDelete(@PathVariable Long habitId) {
        habitService.deleteHabit(habitId);
        return ResponseEntity.noContent().build();
    }
}
