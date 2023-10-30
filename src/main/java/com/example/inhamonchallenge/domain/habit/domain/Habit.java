package com.example.inhamonchallenge.domain.habit.domain;

import com.example.inhamonchallenge.domain.model.BaseTime;
import com.example.inhamonchallenge.domain.model.Category;
import com.example.inhamonchallenge.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
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

    private int totalRecordCnt;

    private int photoRecordCnt;

    private int currentRecordCnt;

    private int reportCnt;
}
