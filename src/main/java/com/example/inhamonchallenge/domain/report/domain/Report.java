package com.example.inhamonchallenge.domain.report.domain;

import com.example.inhamonchallenge.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Long reportedId;

    @Enumerated(EnumType.STRING)
    private ReportType reportType;

    @Enumerated(EnumType.STRING)
    private ReportDescription reportDescription;

    private String additionalDescription;


    @Builder
    public Report(Long id, User user, Long reportedId, ReportType reportType, ReportDescription reportDescription, String additionalDescription) {
        this.id = id;
        this.user = user;
        this.reportedId = reportedId;
        this.reportType = reportType;
        this.reportDescription = reportDescription;
        this.additionalDescription = additionalDescription;
    }
}
