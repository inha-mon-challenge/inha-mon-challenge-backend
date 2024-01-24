package com.example.inhamonchallenge.domain.habit.controller;

import com.example.inhamonchallenge.domain.common.dto.Result;
import com.example.inhamonchallenge.domain.habit.dto.HabitResponse;
import com.example.inhamonchallenge.domain.habit.dto.SearchHabitResponse;
import com.example.inhamonchallenge.domain.habit.service.SearchHabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
                                                                  @RequestParam(required = false) Long cursor,
                                                                  @AuthenticationPrincipal UserDetails userDetails) {
        boolean isLoggedIn = userDetails != null;
        Result<List<SearchHabitResponse>> response = searchHabitService.searchHabit(keyword, cursor, isLoggedIn);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/hashtags")
    ResponseEntity<Result<List<SearchHabitResponse>>> searchByHashtags(@RequestParam String keyword,
                                                                       @RequestParam(required = false) Long cursor,
                                                                       @AuthenticationPrincipal UserDetails userDetails) {
        boolean isLoggedIn = userDetails != null;
        Result<List<SearchHabitResponse>> response = searchHabitService.searchByHashtags(keyword, cursor, isLoggedIn);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/autocomplete")
    ResponseEntity<Result<List<String>>> autoComplete(@RequestParam String keyword) {
        Result<List<String>> response = searchHabitService.autoComplete(keyword);
        return ResponseEntity.ok(response);
    }
}
