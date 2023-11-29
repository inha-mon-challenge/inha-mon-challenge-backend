package com.example.inhamonchallenge.domain.record.service;

import com.example.inhamonchallenge.domain.common.PrivacySetting;
import com.example.inhamonchallenge.domain.common.exception.DeleteDeniedException;
import com.example.inhamonchallenge.domain.common.exception.UpdateDeniedException;
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
import com.example.inhamonchallenge.global.security.SecurityUtil;
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
        Record record = SaveRecordRequest.toEntity(request, habit, user);
        Record savedRecord = recordRepository.save(record);
        return SaveRecordResponse.from(savedRecord);
    }


    public void deleteHabit(Long recordId) {
        User user = userRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(NotFoundUserException::new);
        Record record = recordRepository.findById(recordId).orElseThrow(NotFoundRecordException::new);
        if (record.getUser().getId() != user.getId()) {
            throw new DeleteDeniedException();
        }
        recordRepository.deleteById(recordId);
    }

    public void changeRecordPrivacy(Long recordId, PrivacySetting privacySetting) {
        User user = userRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(NotFoundUserException::new);
        Record record = recordRepository.findById(recordId).orElseThrow(NotFoundHabitException::new);
        if (record.getUser().getId() != user.getId()) {
            throw new UpdateDeniedException();
        }
        record.changePrivacy(privacySetting);
    }
}
