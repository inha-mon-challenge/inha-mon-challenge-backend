package com.example.inhamonchallenge.domain.record.domain;

import com.example.inhamonchallenge.domain.habit.domain.Habit;
import com.example.inhamonchallenge.domain.common.BaseTime;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@DiscriminatorColumn(name = "RECORD")
public class Record extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habit_id")
    private Habit habit;

    private String image;

    private String content;

    private String hashtags;

    private int reportCnt;

    private int likeCnt;
}
