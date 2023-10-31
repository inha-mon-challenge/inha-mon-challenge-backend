package com.example.inhamonchallenge.domain.habit.controller;

import com.example.inhamonchallenge.domain.habit.dto.HabitResponse;
import com.example.inhamonchallenge.domain.habit.dto.SaveHabitRequest;
import com.example.inhamonchallenge.domain.habit.dto.SaveHabitResponse;
import com.example.inhamonchallenge.domain.habit.service.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/habits")
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;

    @GetMapping("/{habitId}")
    ResponseEntity<HabitResponse> habitDetails(@PathVariable Long habitId) {
        HabitResponse response = habitService.findHabit(habitId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    ResponseEntity<SaveHabitResponse> habitAdd(@ModelAttribute SaveHabitRequest request) {
        SaveHabitResponse response = habitService.addHabit(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
