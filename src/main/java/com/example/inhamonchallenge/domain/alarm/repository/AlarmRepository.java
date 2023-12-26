package com.example.inhamonchallenge.domain.alarm.repository;

import com.example.inhamonchallenge.domain.alarm.domain.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    List<Alarm> findAllByReceiveUserId(Long userId);
}
