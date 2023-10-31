package com.example.inhamonchallenge.domain.like.domain;

import com.example.inhamonchallenge.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@DiscriminatorColumn(name = "feed_type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Long feedId;
}
