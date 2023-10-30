package com.example.inhamonchallenge.domain.comment.domain;

import com.example.inhamonchallenge.domain.habit.domain.Habit;
import com.example.inhamonchallenge.domain.model.BaseTime;
import com.example.inhamonchallenge.domain.model.FeedType;
import com.example.inhamonchallenge.domain.record.domain.Record;
import com.example.inhamonchallenge.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Comment extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habit_id")
    private Habit habit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "record_id")
    private Record record;

    private String content;

    private FeedType feedType;

    private int reportCnt;
}
