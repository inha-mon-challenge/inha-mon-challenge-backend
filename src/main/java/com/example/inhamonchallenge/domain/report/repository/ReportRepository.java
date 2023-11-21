package com.example.inhamonchallenge.domain.report.repository;

import com.example.inhamonchallenge.domain.common.FeedType;
import com.example.inhamonchallenge.domain.report.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReportRepository extends JpaRepository<Report, Long> {

    boolean existsByFeedIdAndFeedTypeAndUserId(Long feedId, FeedType feedType, Long userId);

    @Modifying
    @Query("UPDATE Habit h SET h.reportCnt = h.reportCnt + 1 WHERE h.id = :feedId")
    int increaseHabitReport(@Param("feedId") Long feedId);

    @Modifying
    @Query("UPDATE Record r SET r.reportCnt = r.reportCnt + 1 WHERE r.id = :feedId")
    int increaseRecordReport(@Param("feedId") Long feedId);
}
