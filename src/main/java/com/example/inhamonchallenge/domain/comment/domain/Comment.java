package com.example.inhamonchallenge.domain.comment.domain;

import com.example.inhamonchallenge.domain.common.BaseTime;
import com.example.inhamonchallenge.domain.common.FeedType;
import com.example.inhamonchallenge.domain.habit.domain.Habit;
import com.example.inhamonchallenge.domain.record.domain.Record;
import com.example.inhamonchallenge.domain.user.domain.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
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

    @Enumerated(EnumType.STRING)
    private FeedType feedType;

    private int reportCnt;

    @Builder
    public Comment(Long id, User user, Habit habit, Record record,
                   String content, FeedType feedType, int reportCnt) {
        this.id = id;
        this.user = user;
        this.habit = habit;
        this.record = record;
        this.content = content;
        this.feedType = feedType;
        this.reportCnt = reportCnt;
    }
}
