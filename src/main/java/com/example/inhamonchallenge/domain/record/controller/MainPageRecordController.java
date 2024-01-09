package com.example.inhamonchallenge.domain.record.controller;

import com.example.inhamonchallenge.domain.common.dto.Result;
import com.example.inhamonchallenge.domain.habit.dto.HabitResponse;
import com.example.inhamonchallenge.domain.habit.dto.RecommendHabitResponse;
import com.example.inhamonchallenge.domain.record.dto.RecordResponse;
import com.example.inhamonchallenge.domain.record.service.MainPageRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/records/main")
@RequiredArgsConstructor
public class MainPageRecordController {

    private final MainPageRecordService mainPageRecordService;

    @GetMapping
    ResponseEntity<Result<List<RecordResponse>>> getMainRecords(@RequestParam(name = "cursor", required = false) Long cursor,
                                                                @AuthenticationPrincipal UserDetails userDetails) {
        boolean isLoggedIn = userDetails != null;
        return ResponseEntity.ok(mainPageRecordService.getMainRecords(cursor, isLoggedIn));

    }

    @GetMapping("/following")
    ResponseEntity<Result<List<RecordResponse>>> getFollowingRecords(@RequestParam(name = "cursor", required = false) Long cursor) {
        return ResponseEntity.ok(mainPageRecordService.getFollowingRecords(cursor));
    }

    @GetMapping("/non-following")
    ResponseEntity<Result<List<RecordResponse>>> getNonFollowingRecords(@RequestParam(required = false) Long cursor, @RequestParam int count) {
        return ResponseEntity.ok(mainPageRecordService.getNonFollowingRecords(cursor, count));
    }

    @GetMapping("/public")
    ResponseEntity<Result<List<RecordResponse>>> getPublicRecords(@RequestParam(required = false) Long cursor) {
        return ResponseEntity.ok(mainPageRecordService.getPublicRecords(cursor));
    }

}
