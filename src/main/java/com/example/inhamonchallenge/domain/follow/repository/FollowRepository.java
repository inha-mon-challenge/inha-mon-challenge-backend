package com.example.inhamonchallenge.domain.follow.repository;

import com.example.inhamonchallenge.domain.follow.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    boolean existsByFollowerIdAndFollowingId(Long currentMemberId, Long userId);
}
