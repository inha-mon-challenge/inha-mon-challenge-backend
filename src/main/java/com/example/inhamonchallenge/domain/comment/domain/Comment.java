package com.example.inhamonchallenge.domain.comment.domain;

import com.example.inhamonchallenge.domain.common.BaseTime;
import com.example.inhamonchallenge.domain.common.FeedType;
import com.example.inhamonchallenge.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> replies = new ArrayList<>();

    private Long feedId;

    private String content;

    @Enumerated(EnumType.STRING)
    private FeedType feedType;

    private int reportCnt;


    @Builder
    public Comment(Long id, User user, Comment comment, Long feedId, String content, FeedType feedType, int reportCnt) {
        this.id = id;
        this.user = user;
        this.parent = comment;
        this.feedId = feedId;
        this.content = content;
        this.feedType = feedType;
        this.reportCnt = reportCnt;
    }

    public void update(String content) {
        this.content = content;
    }
}
