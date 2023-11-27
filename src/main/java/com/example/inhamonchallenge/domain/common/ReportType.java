package com.example.inhamonchallenge.domain.common;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ReportType {
    HABIT("습관"),
    RECORD("기록"),
    COMMENT("댓글");

    private final String reportType;
}
