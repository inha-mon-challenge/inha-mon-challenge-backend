package com.example.inhamonchallenge.domain.report.dto;

import com.example.inhamonchallenge.domain.report.domain.ReportDescription;
import com.example.inhamonchallenge.domain.report.domain.ReportType;
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

    private Long reportedId;
    private ReportType reportType;
    private String reportDescription;
    private String additionalDescription;

    public static Report toEntity(ReportRequest request, User user) {
        String additionalDescription;
        if (request.reportDescription.equals("기타 사유")) {
            additionalDescription = request.additionalDescription;
        } else {
            additionalDescription = null;
        }

        return Report.builder()
                .user(user)
                .reportedId(request.getReportedId())
                .reportType(request.getReportType())
                .reportDescription(ReportDescription.fromString(request.getReportDescription()))
                .additionalDescription(additionalDescription)
                .build();
    }
}
