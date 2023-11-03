package com.example.inhamonchallenge.domain.likes.repository;

import com.example.inhamonchallenge.domain.likes.domain.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, Long> {
}
