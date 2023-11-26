package com.example.inhamonchallenge.domain.habit.service;

import com.example.inhamonchallenge.domain.common.dto.Result;
import com.example.inhamonchallenge.domain.common.exception.DeleteDeniedException;
import com.example.inhamonchallenge.domain.common.exception.UpdateDeniedException;
import com.example.inhamonchallenge.domain.habit.domain.Habit;
import com.example.inhamonchallenge.domain.habit.dto.HabitAndRecordResponse;
import com.example.inhamonchallenge.domain.habit.dto.HabitResponse;
import com.example.inhamonchallenge.domain.habit.dto.SaveHabitRequest;
import com.example.inhamonchallenge.domain.habit.dto.SaveHabitResponse;
import com.example.inhamonchallenge.domain.habit.exception.NotFoundHabitException;
import com.example.inhamonchallenge.domain.habit.repository.HabitRepository;
import com.example.inhamonchallenge.domain.record.domain.Record;
import com.example.inhamonchallenge.domain.record.repository.RecordRepository;
import com.example.inhamonchallenge.domain.user.domain.User;
import com.example.inhamonchallenge.domain.user.exception.NotFoundUserException;
import com.example.inhamonchallenge.domain.user.repository.UserRepository;
import com.example.inhamonchallenge.global.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class HabitService {

    private final HabitRepository habitRepository;
    private final RecordRepository recordRepository;
    private final UserRepository userRepository;

    public HabitAndRecordResponse getHabit(Long habitId) {
        Habit habit = habitRepository.findById(habitId).orElseThrow(NotFoundHabitException::new);
        List<Record> records = recordRepository.findByHabitId(habitId);
        return HabitAndRecordResponse.from(habit, records);
    }

    public SaveHabitResponse addHabit(SaveHabitRequest request) {
        User user = userRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(NotFoundUserException::new);
        Habit habit = SaveHabitRequest.toEntity(request, user);
        Habit savedHabit = habitRepository.save(habit);
        return SaveHabitResponse.from(savedHabit);
    }

    public Result<List<HabitAndRecordResponse>> getAllHabitsAndRecordsByUserId(Long userId) {
        userRepository.findById(userId).orElseThrow(NotFoundUserException::new);
        List<Object[]> results = habitRepository.findHabitsAndRecordsByUserId(userId);

        Map<Habit, List<Record>> mp = new HashMap<>();
        for (Object[] result : results) {
            Habit habit = (Habit) result[0];
            Record record = (Record) result[1];
            if (!mp.containsKey(habit)) {
                mp.put(habit, new ArrayList<>(List.of(record)));
            } else {
                mp.get(habit).add(record);
            }
        }

        List<HabitAndRecordResponse> response = mp.entrySet().stream()
                .map(m -> HabitAndRecordResponse.from(m.getKey(), m.getValue()))
                .collect(Collectors.toList());

        return new Result<>(response);
    }

    public SaveHabitResponse updateHabit(SaveHabitRequest request, Long habitId) {
        User user = userRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(NotFoundUserException::new);
        Habit habit = habitRepository.findById(habitId).orElseThrow(NotFoundHabitException::new);
        if (habit.getUser().getId() != user.getId()) {
            throw new UpdateDeniedException();
        }
        habit.updateHabit(request.getContent(), "xxx", request.getCategory(), String.join(",", request.getHashTag()));
        return SaveHabitResponse.from(habit);
    }

    public void deleteHabit(Long habitId) {
        User user = userRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(NotFoundUserException::new);
        Habit habit = habitRepository.findById(habitId).orElseThrow(NotFoundHabitException::new);
        if (habit.getUser().getId() != user.getId()) {
            throw new DeleteDeniedException();
        }
        habitRepository.deleteById(habitId);
    }

    private String[] getHashtagsArray(String hashtags) {
        if (hashtags != null) {
            return hashtags.split(",");
        } else {
            return new String[0];
        }
    }
}
