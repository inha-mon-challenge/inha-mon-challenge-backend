package com.example.inhamonchallenge.domain.habit.controller;

import com.example.inhamonchallenge.domain.habit.dto.HabitResponse;
import com.example.inhamonchallenge.domain.habit.dto.SaveHabitRequest;
import com.example.inhamonchallenge.domain.habit.dto.SaveHabitResponse;
import com.example.inhamonchallenge.domain.habit.service.HabitService;
import com.example.inhamonchallenge.domain.common.dto.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;

    @GetMapping("/habits/{habitId}")
    ResponseEntity<HabitResponse> habitDetails(@PathVariable Long habitId) {
        HabitResponse response = habitService.getHabit(habitId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/habits")
    ResponseEntity<SaveHabitResponse> habitAdd(@ModelAttribute SaveHabitRequest request) {
        SaveHabitResponse response = habitService.addHabit(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/users/{userId}/habits")
    ResponseEntity<Result<List<HabitResponse>>> habitList(@PathVariable Long userId) {
        Result<List<HabitResponse>> habitList = habitService.getAllHabitsByUserId(userId);
        return ResponseEntity.ok(habitList);
    }

    @PutMapping("/habits/{habitId}")
    ResponseEntity<SaveHabitResponse> habitUpdate(@ModelAttribute SaveHabitRequest request, @PathVariable Long habitId) {
        SaveHabitResponse response = habitService.updateHabit(request, habitId);
        return ResponseEntity.ok(response);
    }
}
