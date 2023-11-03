package com.example.inhamonchallenge.domain.likes.repository;

import com.example.inhamonchallenge.domain.common.FeedType;
import com.example.inhamonchallenge.domain.likes.domain.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    @Modifying
    @Query("UPDATE Habit h SET h.likeCnt = h.likeCnt + 1 WHERE h.id = :feedId")
    int increaseHabitLikes(@Param("feedId") Long feedId);

    @Modifying
    @Query("UPDATE Habit h SET h.likeCnt = h.likeCnt - 1 WHERE h.id = :feedId")
    int decreaseHabitLikes(@Param("feedId") Long feedId);

    @Modifying
    @Query("UPDATE Record r SET r.likeCnt = r.likeCnt + 1 WHERE r.id = :feedId")
    int increaseRecordLikes(@Param("feedId") Long feedId);

    @Modifying
    @Query("UPDATE Record r SET r.likeCnt = r.likeCnt - 1 WHERE r.id = :feedId")
    int decreaseRecordLikes(@Param("feedId") Long feedId);
}
