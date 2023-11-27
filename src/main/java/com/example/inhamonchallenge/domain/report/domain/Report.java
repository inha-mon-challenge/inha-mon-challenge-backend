package com.example.inhamonchallenge.domain.report.domain;

import com.example.inhamonchallenge.domain.common.FeedType;
import com.example.inhamonchallenge.domain.common.ReportType;
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

    @Builder
    public Report(Long id, User user, Long reportedId, ReportType reportType) {
        this.id = id;
        this.user = user;
        this.reportedId = reportedId;
        this.reportType = reportType;
    }
}
