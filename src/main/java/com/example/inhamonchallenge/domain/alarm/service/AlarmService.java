package com.example.inhamonchallenge.domain.alarm.service;

import com.example.inhamonchallenge.domain.alarm.domain.Alarm;
import com.example.inhamonchallenge.domain.alarm.domain.AlarmStatus;
import com.example.inhamonchallenge.domain.alarm.dto.AlarmResponse;
import com.example.inhamonchallenge.domain.alarm.exception.NotFoundAlarmException;
import com.example.inhamonchallenge.domain.alarm.repository.AlarmRepository;
import com.example.inhamonchallenge.domain.common.dto.Result;
import com.example.inhamonchallenge.domain.user.domain.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.inhamonchallenge.global.security.SecurityUtil.*;
import static java.util.stream.Collectors.toList;

@Service
@Transactional
@RequiredArgsConstructor
public class AlarmService {

    private final AlarmRepository alarmRepository;

    public void sendAlarm(User sendUser, User receiveUser, AlarmStatus alarmStatus) {
        if (alarmStatus == AlarmStatus.FOLLOW_REQUEST) {
            // 팔로우 요청 알람 보내기
            Alarm alarm = Alarm.builder()
                    .sendUser(sendUser)
                    .receiveUser(receiveUser)
                    .status(alarmStatus)
                    .isRead(false)
                    .build();
            alarmRepository.save(alarm);
        }
    }

    public Result<List<AlarmResponse>> getAlarms() {
        List<Alarm> alarms = alarmRepository.findAllByReceiveUserId(getCurrentMemberId());
        List<AlarmResponse> responses = alarms.stream()
                .map(AlarmResponse::of)
                .collect(toList());
        return new Result<>(responses);
    }

    public void readAlarms(Long alarmId) {
        Alarm alarm = alarmRepository.findById(alarmId).orElseThrow(NotFoundAlarmException::new);
        alarm.changeReadStatus();
    }
}
