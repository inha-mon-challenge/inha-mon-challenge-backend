package com.example.inhamonchallenge.domain.report.repository;

import com.example.inhamonchallenge.domain.common.FeedType;
import com.example.inhamonchallenge.domain.common.ReportType;
import com.example.inhamonchallenge.domain.report.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReportRepository extends JpaRepository<Report, Long> {

    boolean existsByReportedIdAndReportTypeAndUserId(Long reportedId, ReportType reportType, Long userId);

    @Modifying
    @Query("UPDATE Habit h SET h.reportCnt = h.reportCnt + 1 WHERE h.id = :id")
    int increaseHabitReport(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Record r SET r.reportCnt = r.reportCnt + 1 WHERE r.id = :id")
    int increaseRecordReport(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Comment c SET c.reportCnt = c.reportCnt + 1 WHERE c.id = :id")
    int increaseCommentReport(@Param("id") Long id);
}
