package com.example.inhamonchallenge.domain.record.controller;

import com.example.inhamonchallenge.domain.record.dto.RecordResponse;
import com.example.inhamonchallenge.domain.record.dto.SaveRecordRequest;
import com.example.inhamonchallenge.domain.record.dto.SaveRecordResponse;
import com.example.inhamonchallenge.domain.record.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @GetMapping("/records/{recordId}")
    ResponseEntity<RecordResponse> recordDetails(@PathVariable Long recordId) {
        RecordResponse response = recordService.getRecord(recordId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/habits/{habitId}/records")
    ResponseEntity<SaveRecordResponse> recordAdd(@ModelAttribute SaveRecordRequest request, @PathVariable Long habitId) {
        SaveRecordResponse response = recordService.addRecord(request, habitId);
        return ResponseEntity.status(CREATED).body(response);
    }

    @DeleteMapping("/records/{recordId}")
    ResponseEntity<Void> recordDelete(@PathVariable Long recordId){
        recordService.deleteHabit(recordId);
        return ResponseEntity.noContent().build();
    }
}
