package com.example.inhamonchallenge.domain.habit.domain;

import com.example.inhamonchallenge.domain.model.BaseTime;
import com.example.inhamonchallenge.domain.model.Category;
import com.example.inhamonchallenge.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@DiscriminatorColumn(name = "HABIT")
@NoArgsConstructor
public class Habit extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String content;

    private String image;

    private String hashtags;

    private int totalRecordCnt;

    private int photoRecordCnt;

    private int currentRecordCnt;

    private int reportCnt;

    private int likeCnt;

    @Builder
    public Habit(Long id, User user, Category category, String content, String image, String hashtags,
                 int totalRecordCnt, int photoRecordCnt, int currentRecordCnt, int reportCnt, int likeCnt) {
        this.id = id;
        this.user = user;
        this.category = category;
        this.content = content;
        this.image = image;
        this.hashtags = hashtags;
        this.totalRecordCnt = totalRecordCnt;
        this.photoRecordCnt = photoRecordCnt;
        this.currentRecordCnt = currentRecordCnt;
        this.reportCnt = reportCnt;
        this.likeCnt = likeCnt;
    }
}
