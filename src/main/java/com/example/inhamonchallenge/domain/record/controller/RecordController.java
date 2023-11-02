package com.example.inhamonchallenge.domain.record.controller;

import com.example.inhamonchallenge.domain.record.dto.RecordResponse;
import com.example.inhamonchallenge.domain.record.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @GetMapping("/records/{recordId}")
    ResponseEntity<RecordResponse> recordDetails(@PathVariable Long recordId){
        RecordResponse response = recordService.getRecord(recordId);
        return ResponseEntity.ok(response);
    }
}
