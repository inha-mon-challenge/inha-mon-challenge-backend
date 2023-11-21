package com.example.inhamonchallenge.domain.report.controller;

import com.example.inhamonchallenge.domain.report.dto.ReportRequest;
import com.example.inhamonchallenge.domain.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/report")
public class ReportController {

    private final ReportService reportService;

    @PostMapping
    ResponseEntity<Void> reportAdd(@RequestBody ReportRequest request){
        reportService.addReport(request);
        return ResponseEntity.noContent().build();
    }
}
