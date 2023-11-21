package com.example.inhamonchallenge.domain.report.dto;

import com.example.inhamonchallenge.domain.common.FeedType;
import com.example.inhamonchallenge.domain.report.domain.Report;
import com.example.inhamonchallenge.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReportRequest {

    private Long feedId;
    private FeedType feedType;

    public static Report toEntity(ReportRequest request, User user) {
        return Report.builder()
                .user(user)
                .feedId(request.getFeedId())
                .feedType(request.getFeedType())
                .build();
    }
}
