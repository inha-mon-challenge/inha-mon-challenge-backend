package com.example.inhamonchallenge.domain.alarm.dto;

import com.example.inhamonchallenge.domain.alarm.domain.Alarm;
import com.example.inhamonchallenge.domain.alarm.domain.AlarmStatus;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AlarmResponse {

    private Long id;
    private AlarmStatus status;
    private Long sendUserId;
    private String sendUserName;
    private Long receiveUserId;
    private String receiveUserName;
    private boolean isRead;

    public static AlarmResponse of(Alarm alarm) {
        return AlarmResponse.builder()
                .id(alarm.getId())
                .status(alarm.getStatus())
                .sendUserId(alarm.getSendUser().getId())
                .sendUserName(alarm.getReceiveUser().getName())
                .receiveUserId(alarm.getReceiveUser().getId())
                .receiveUserName(alarm.getReceiveUser().getName())
                .isRead(alarm.isRead())
                .build();
    }

}
