package com.example.inhamonchallenge.domain.hashtag.domain;

import com.example.inhamonchallenge.domain.habit.domain.Habit;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class HashTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habit_id")
    private Habit habit;

    private String tag;
}
