package com.example.inhamonchallenge.domain.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum FeedType {
    HABIT("습관"),
    RECORD("기록");

    private final String feedType;
}
