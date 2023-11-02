package com.example.inhamonchallenge.domain.record.service;

import com.example.inhamonchallenge.domain.habit.domain.Habit;
import com.example.inhamonchallenge.domain.habit.exception.NotFoundHabitException;
import com.example.inhamonchallenge.domain.habit.repository.HabitRepository;
import com.example.inhamonchallenge.domain.record.domain.Record;
import com.example.inhamonchallenge.domain.record.dto.RecordResponse;
import com.example.inhamonchallenge.domain.record.dto.SaveRecordRequest;
import com.example.inhamonchallenge.domain.record.dto.SaveRecordResponse;
import com.example.inhamonchallenge.domain.record.exception.NotFoundRecordException;
import com.example.inhamonchallenge.domain.record.repository.RecordRepository;
import com.example.inhamonchallenge.domain.user.domain.User;
import com.example.inhamonchallenge.domain.user.exception.NotFoundUserException;
import com.example.inhamonchallenge.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.inhamonchallenge.global.security.SecurityUtil.getCurrentMemberId;

@Service
@Transactional
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;
    private final UserRepository userRepository;
    private final HabitRepository habitRepository;

    public RecordResponse getRecord(Long recordId) {
        Record record = recordRepository.findById(recordId).orElseThrow(NotFoundRecordException::new);
        return RecordResponse.from(record);
    }

    public SaveRecordResponse addRecord(SaveRecordRequest request, Long habitId) {
        User user = userRepository.findById(getCurrentMemberId()).orElseThrow(NotFoundUserException::new);
        Habit habit = habitRepository.findById(habitId).orElseThrow(NotFoundHabitException::new);
        Record record = SaveRecordRequest.toEntity(request, habit, user, "xxx");
        Record savedRecord = recordRepository.save(record);
        return SaveRecordResponse.from(savedRecord);
    }


}
