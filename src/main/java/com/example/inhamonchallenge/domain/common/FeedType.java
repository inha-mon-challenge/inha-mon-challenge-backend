package com.example.inhamonchallenge.domain.common;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum FeedType {
    HABIT("습관"),
    RECORD("기록");

    private final String feedType;
}
