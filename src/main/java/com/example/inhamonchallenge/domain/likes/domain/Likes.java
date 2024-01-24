package com.example.inhamonchallenge.domain.likes.domain;

import com.example.inhamonchallenge.domain.common.FeedType;
import com.example.inhamonchallenge.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Long feedId;

    @Enumerated(EnumType.STRING)
    private FeedType feedType;

    @Builder
    public Likes(Long id, User user, Long feedId, FeedType feedType) {
        this.id = id;
        this.user = user;
        this.feedId = feedId;
        this.feedType = feedType;
    }
}
