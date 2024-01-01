package com.example.inhamonchallenge.domain.follow.repository;

import com.example.inhamonchallenge.domain.follow.controller.FollowStatus;
import com.example.inhamonchallenge.domain.follow.domain.Follow;
import com.example.inhamonchallenge.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    boolean existsByFollowerIdAndFollowingId(Long currentMemberId, Long userId);

    @Query("SELECT f.following FROM Follow f WHERE f.follower.id = :userId " +
            "AND f.status = 'ACCEPTED' AND f.following.isDeleted = false")
    List<User> findAcceptedFollowingByUserId(@Param("userId") Long userId);

    @Query("SELECT f.follower FROM Follow f WHERE f.following.id = :userId " +
            "AND f.status = 'ACCEPTED' AND f.follower.isDeleted = false")
    List<User> findAllByFollowingId(@Param("userId") Long userId);

    @Query("SELECT f FROM Follow f WHERE f.following.id = :userId AND f.status = 'PENDING' AND f.follower.isDeleted = false")
    List<Follow> findAllByFollowingIdAndStatus(@Param("userId") Long userId);

    @Query("SELECT COUNT(f) FROM Follow f WHERE f.follower.id = :userId AND f.status = 'ACCEPTED'")
    Long countByFollowerIdAndStatusAccepted(@Param("userId") Long userId);

    void deleteByFollowerIdOrFollowingId(Long id, Long id1);
}
