package com.example.inhamonchallenge.domain.habit.domain;

import com.example.inhamonchallenge.domain.common.BaseTime;
import com.example.inhamonchallenge.domain.common.Category;
import com.example.inhamonchallenge.domain.common.PrivacySetting;
import com.example.inhamonchallenge.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Habit extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String title;

    private String content;

    @Enumerated(EnumType.STRING)
    private Frequency frequency;

    private String hashtags;

    private int totalRecordCnt;

    private int photoRecordCnt;

    private int currentRecordCnt;

    private int reportCnt;

    private int likeCnt;

    @Enumerated(EnumType.STRING)
    private PrivacySetting privacy;

    @Builder
    public Habit(Long id, User user, Category category, String title, String content,
                 Frequency frequency, String hashtags, int totalRecordCnt, int photoRecordCnt,
                 int currentRecordCnt, int reportCnt, int likeCnt, PrivacySetting privacy) {
        this.id = id;
        this.user = user;
        this.category = category;
        this.title = title;
        this.content = content;
        this.frequency = frequency;
        this.hashtags = hashtags;
        this.totalRecordCnt = totalRecordCnt;
        this.photoRecordCnt = photoRecordCnt;
        this.currentRecordCnt = currentRecordCnt;
        this.reportCnt = reportCnt;
        this.likeCnt = likeCnt;
        this.privacy = privacy;
    }

    public void updateHabit(String content, String title, String category, String hashtags) {
        this.content = content;
        this.title = title;
        this.category = Category.valueOf(category);
        this.hashtags = hashtags;
    }

    public void changePrivacy(PrivacySetting privacy) {
        this.privacy = privacy;

    }
}
