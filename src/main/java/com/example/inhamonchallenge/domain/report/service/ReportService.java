package com.example.inhamonchallenge.domain.report.service;

import com.example.inhamonchallenge.domain.common.FeedType;
import com.example.inhamonchallenge.domain.habit.repository.HabitRepository;
import com.example.inhamonchallenge.domain.record.repository.RecordRepository;
import com.example.inhamonchallenge.domain.report.dto.ReportRequest;
import com.example.inhamonchallenge.domain.report.repository.ReportRepository;
import com.example.inhamonchallenge.domain.user.domain.User;
import com.example.inhamonchallenge.domain.user.repository.UserRepository;
import com.example.inhamonchallenge.global.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.inhamonchallenge.domain.common.FeedType.*;
import static com.example.inhamonchallenge.global.security.SecurityUtil.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;

    public void addReport(ReportRequest request) {
        if(reportRepository.existsByFeedIdAndFeedTypeAndUserId(request.getFeedId(), request.getFeedType(), getCurrentMemberId())){
            return;
        }
        User user = userRepository.findById(getCurrentMemberId()).orElseThrow();
        if(request.getFeedType() == HABIT){
            reportRepository.increaseHabitReport(request.getFeedId());
        }
        else if(request.getFeedType() == RECORD){
            reportRepository.increaseRecordReport(request.getFeedId());
        }
        reportRepository.save(request.toEntity(request, user));
    }
}
