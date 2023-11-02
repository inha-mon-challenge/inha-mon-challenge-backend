package com.example.inhamonchallenge.domain.record.domain;

import com.example.inhamonchallenge.domain.common.BaseTime;
import com.example.inhamonchallenge.domain.habit.domain.Habit;
import com.example.inhamonchallenge.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@DiscriminatorColumn(name = "RECORD")
@NoArgsConstructor
public class Record extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habit_id")
    private Habit habit;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String image;

    private String content;

    private String hashtags;

    private int reportCnt;

    private int likeCnt;

    @Builder
    public Record(Long id, Habit habit, User user, String image, String content, String hashtags, int reportCnt, int likeCnt) {
        this.id = id;
        this.habit = habit;
        this.user = user;
        this.image = image;
        this.content = content;
        this.hashtags = hashtags;
        this.reportCnt = reportCnt;
        this.likeCnt = likeCnt;
    }
}
