package com.example.inhamonchallenge.domain.alarm.controller;

import com.example.inhamonchallenge.domain.alarm.dto.AlarmResponse;
import com.example.inhamonchallenge.domain.alarm.service.AlarmService;
import com.example.inhamonchallenge.domain.common.dto.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/read")
    ResponseEntity<Void> readAlarms(@RequestParam Long alarmId) {
        alarmService.readAlarms(alarmId);
        return ResponseEntity.noContent().build();
    }

}
