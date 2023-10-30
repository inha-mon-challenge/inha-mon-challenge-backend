package com.example.inhamonchallenge.domain.comment.repository;

import com.example.inhamonchallenge.domain.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
