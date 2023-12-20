package com.example.inhamonchallenge.domain.alarm.service;

import com.example.inhamonchallenge.domain.alarm.domain.Alarm;
import com.example.inhamonchallenge.domain.alarm.domain.AlarmStatus;
import com.example.inhamonchallenge.domain.alarm.repository.AlarmRepository;
import com.example.inhamonchallenge.domain.user.domain.User;
import com.example.inhamonchallenge.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AlarmService {

    private final AlarmRepository alarmRepository;
    private final UserRepository userRepository;

    public void sendAlarm(User sendUser, User receiveUser, AlarmStatus alarmStatus) {
        if(alarmStatus == AlarmStatus.FOLLOW_REQUEST) {
            // 팔로우 요청 알람 보내기
            Alarm alarm = Alarm.builder()
                    .user(receiveUser)
                    .status(alarmStatus)
                    .build();
            alarmRepository.save(alarm);
        }
    }
}
