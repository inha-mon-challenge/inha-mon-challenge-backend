package com.example.inhamonchallenge.domain.report.service;

import com.example.inhamonchallenge.domain.report.dto.ReportRequest;
import com.example.inhamonchallenge.domain.report.repository.ReportRepository;
import com.example.inhamonchallenge.domain.user.domain.User;
import com.example.inhamonchallenge.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.inhamonchallenge.domain.report.domain.ReportType.*;
import static com.example.inhamonchallenge.global.security.SecurityUtil.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;

    public void addReport(ReportRequest request) {
        if(reportRepository.existsByReportedIdAndReportTypeAndUserId(request.getReportedId(), request.getReportType(), getCurrentMemberId())){
            return;
        }
        User user = userRepository.findById(getCurrentMemberId()).orElseThrow();
        if(request.getReportType() == HABIT){
            reportRepository.increaseHabitReport(request.getReportedId());
        }
        else if(request.getReportType() == RECORD){
            reportRepository.increaseRecordReport(request.getReportedId());
        }
        else if(request.getReportType() == COMMENT){
            reportRepository.increaseCommentReport(request.getReportedId());
        }
        reportRepository.save(request.toEntity(request, user));
    }
}
