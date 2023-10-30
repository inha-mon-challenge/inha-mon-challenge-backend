package com.example.inhamonchallenge.domain.follow.domain;

import com.example.inhamonchallenge.domain.model.BaseCreateTime;
import com.example.inhamonchallenge.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Follow extends BaseCreateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "follower_id")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "following_id")
    private User following;
}
