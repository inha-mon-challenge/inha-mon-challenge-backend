package com.example.inhamonchallenge.domain.comment.repository;

import com.example.inhamonchallenge.domain.comment.domain.Comment;
import com.example.inhamonchallenge.domain.common.FeedType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByFeedTypeAndFeedIdAndParentIsNullOrderByCreatedAt(FeedType feedType, Long feedId);

    void deleteByUserId(Long id);
}
