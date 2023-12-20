package com.example.inhamonchallenge.domain.alarm.repository;

import com.example.inhamonchallenge.domain.alarm.domain.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
}
