package com.example.inhamonchallenge.domain.alarm.controller;

import com.example.inhamonchallenge.domain.alarm.dto.AlarmResponse;
import com.example.inhamonchallenge.domain.alarm.service.AlarmService;
import com.example.inhamonchallenge.domain.common.dto.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/alarm")
public class AlarmController {

    private final AlarmService alarmService;

    @GetMapping
    ResponseEntity<Result<List<AlarmResponse>>> getAlarms() {
        return ResponseEntity.ok(alarmService.getAlarms());
    }

}
