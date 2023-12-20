package com.example.inhamonchallenge.domain.alarm.domain;

import com.example.inhamonchallenge.domain.common.BaseCreateTime;
import com.example.inhamonchallenge.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Alarm extends BaseCreateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "send_user_id")
    private User sendUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receive_user_id")
    private User receiveUser;

    @Enumerated(EnumType.STRING)
    private AlarmStatus status;

    private boolean isRead;

    @Builder

    public Alarm(Long id, User sendUser, User receiveUser, AlarmStatus status, boolean isRead) {
        this.id = id;
        this.sendUser = sendUser;
        this.receiveUser = receiveUser;
        this.status = status;
        this.isRead = isRead;
    }
}
