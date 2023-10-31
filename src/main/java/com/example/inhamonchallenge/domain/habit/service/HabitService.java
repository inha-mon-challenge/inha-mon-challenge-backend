package com.example.inhamonchallenge.domain.habit.service;

import com.example.inhamonchallenge.domain.habit.domain.Habit;
import com.example.inhamonchallenge.domain.habit.dto.HabitResponse;
import com.example.inhamonchallenge.domain.habit.exception.HabitNotFoundException;
import com.example.inhamonchallenge.domain.habit.repository.HabitRepository;
import com.example.inhamonchallenge.domain.hashtag.repository.HashTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitService {

    private final HabitRepository habitRepository;
    private final HashTagRepository hashTagRepository;

    public HabitResponse findHabit(Long habitId){
        Habit habit = habitRepository.findById(habitId).orElseThrow(HabitNotFoundException::new);
        List<String> hashtags = hashTagRepository.findAllByHabitId(habitId);
        return HabitResponse.from(habit, hashtags);
    }
}
