package com.example.inhamonchallenge.domain.habit.service;

import com.example.inhamonchallenge.domain.common.dto.Result;
import com.example.inhamonchallenge.domain.habit.domain.Habit;
import com.example.inhamonchallenge.domain.habit.dto.HabitResponse;
import com.example.inhamonchallenge.domain.habit.dto.SearchHabitResponse;
import com.example.inhamonchallenge.domain.habit.repository.HabitRepository;
import com.example.inhamonchallenge.global.redis.RedisService;
import com.example.inhamonchallenge.global.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.inhamonchallenge.global.security.SecurityUtil.*;

@Service
@Transactional
@RequiredArgsConstructor
public class SearchHabitService {

    private final HabitRepository habitRepository;
    private final RedisService redisService;

    public Result<List<SearchHabitResponse>> searchHabit(String keyword, Long cursor, boolean isLoggedIn) {
        if (cursor == null) {
            cursor = Long.MAX_VALUE;
        }
        List<Habit> habits;
        if (isLoggedIn) {
            habits = habitRepository.searchHabitsByKeywordForLoggedInUser(keyword, cursor, getCurrentMemberId(), PageRequest.of(0, 10)).getContent();
        } else {
            habits = habitRepository.searchHabitsByKeyword(keyword, cursor, PageRequest.of(0, 10)).getContent();
        }
        return new Result<>(habits.stream()
                .map(SearchHabitResponse::from)
                .collect(Collectors.toList()));
    }

    public Result<List<SearchHabitResponse>> searchByHashtags(String keyword, Long cursor, boolean isLoggedIn) {
        if (cursor == null) {
            cursor = Long.MAX_VALUE;
        }
        List<Habit> habits;
        if (isLoggedIn) {
            habits = habitRepository.searchByHashtagsForLoggedInUser(keyword, cursor, getCurrentMemberId(), PageRequest.of(0, 10)).getContent();
        } else {
            habits = habitRepository.searchByHashtags(keyword, cursor, PageRequest.of(0, 10)).getContent();
        }
        return new Result<>(habits.stream()
                .map(SearchHabitResponse::from)
                .collect(Collectors.toList()));
    }

    public Result<List<String>> autoComplete(String keyword) {
        return new Result<>(redisService.getAutocomplete(keyword));
    }
}
