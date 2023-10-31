package com.example.inhamonchallenge.domain.habit.service;

import com.example.inhamonchallenge.domain.habit.domain.Habit;
import com.example.inhamonchallenge.domain.habit.dto.HabitResponse;
import com.example.inhamonchallenge.domain.habit.dto.SaveHabitRequest;
import com.example.inhamonchallenge.domain.habit.dto.SaveHabitResponse;
import com.example.inhamonchallenge.domain.habit.exception.NotFoundHabitException;
import com.example.inhamonchallenge.domain.habit.repository.HabitRepository;
import com.example.inhamonchallenge.domain.model.dto.Result;
import com.example.inhamonchallenge.domain.user.domain.User;
import com.example.inhamonchallenge.domain.user.exception.NotFoundUserException;
import com.example.inhamonchallenge.domain.user.repository.UserRepository;
import com.example.inhamonchallenge.global.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class HabitService {

    private final HabitRepository habitRepository;
    private final UserRepository userRepository;

    public HabitResponse getHabit(Long habitId) {
        Habit habit = habitRepository.findById(habitId).orElseThrow(NotFoundHabitException::new);
        return HabitResponse.from(habit, List.of(getHashtagsArray(habit.getHashtags())));
    }

    public SaveHabitResponse addHabit(SaveHabitRequest request) {
        User user = userRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(NotFoundUserException::new);
        Habit habit = SaveHabitRequest.toEntity(request, user, "xxx");
        Habit savedHabit = habitRepository.save(habit);
        return SaveHabitResponse.from(savedHabit);
    }

    public Result<List<HabitResponse>> getAllHabitsByUserId(Long userId){
        userRepository.findById(userId).orElseThrow(NotFoundUserException::new);
        List<Habit> habits = habitRepository.findAllByUserId(userId);

        List<HabitResponse> habitResponses = habits.stream()
                .map(habit -> HabitResponse.from(habit, List.of(getHashtagsArray(habit.getHashtags()))))
                .collect(Collectors.toList());

        return new Result<>(habitResponses);
    }

    private String[] getHashtagsArray(String hashtags) {
        if (hashtags != null) {
            return hashtags.split(",");
        } else {
            return new String[0];
        }
    }
}
