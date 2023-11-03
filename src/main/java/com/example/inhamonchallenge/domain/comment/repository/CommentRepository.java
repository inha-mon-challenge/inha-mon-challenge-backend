package com.example.inhamonchallenge.domain.comment.repository;

import com.example.inhamonchallenge.domain.comment.domain.Comment;
import com.example.inhamonchallenge.domain.common.FeedType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c where c.feedId = :feedId and c.feedType = :feedType order by c.createdDateTime desc")
    List<Comment> findAllByFeedIdAnAndFeedType(@Param("feedId") Long feedId, @Param("feedType") FeedType feedType);
}
