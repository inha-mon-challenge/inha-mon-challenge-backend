package com.example.inhamonchallenge.domain.habit.controller;

import com.example.inhamonchallenge.domain.common.dto.Result;
import com.example.inhamonchallenge.domain.habit.dto.HabitResponse;
import com.example.inhamonchallenge.domain.habit.dto.SearchHabitResponse;
import com.example.inhamonchallenge.domain.habit.service.SearchHabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchHabitController {

    private final SearchHabitService searchHabitService;

    @GetMapping
    ResponseEntity<Result<List<SearchHabitResponse>>> searchHabit(@RequestParam String keyword,
                                                                  @RequestParam(required = false) Long cursor) {
        Result<List<SearchHabitResponse>> response = searchHabitService.searchHabit(keyword, cursor);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/hashtags")
    ResponseEntity<Result<List<SearchHabitResponse>>> searchByHashtags(@RequestParam String keyword,
                                                          @RequestParam(required = false) Long cursor) {
        Result<List<SearchHabitResponse>> response = searchHabitService.searchByHashtags(keyword, cursor);
        return ResponseEntity.ok(response);
    }
}
