package com.example.inhamonchallenge.domain.habit.controller;

import com.example.inhamonchallenge.domain.habit.dto.HabitResponse;
import com.example.inhamonchallenge.domain.habit.service.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/habits")
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;

    @GetMapping("/{habitId}")
    ResponseEntity<HabitResponse> habitDetails(@PathVariable Long habitId){
        HabitResponse response = habitService.findHabit(habitId);
        return ResponseEntity.ok(response);
    }
}
