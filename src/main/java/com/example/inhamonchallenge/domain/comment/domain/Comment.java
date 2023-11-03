package com.example.inhamonchallenge.domain.comment.domain;

import com.example.inhamonchallenge.domain.common.BaseTime;
import com.example.inhamonchallenge.domain.common.FeedType;
import com.example.inhamonchallenge.domain.user.domain.User;
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

    private Long feedId;

    private String content;

    @Enumerated(EnumType.STRING)
    private FeedType feedType;

    private int reportCnt;

    @Builder
    public Comment(Long id, User user, Long feedId, String content, FeedType feedType, int reportCnt) {
        this.id = id;
        this.user = user;
        this.feedId = feedId;
        this.content = content;
        this.feedType = feedType;
        this.reportCnt = reportCnt;
    }
}
