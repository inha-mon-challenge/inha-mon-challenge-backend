package com.example.inhamonchallenge.domain.habit.repository;

import com.example.inhamonchallenge.domain.habit.domain.Habit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface HabitRepository extends JpaRepository<Habit, Long> {

    @Query("SELECT h, r FROM Habit h LEFT JOIN Record r ON h.id = r.habit.id " +
            "WHERE h.user.id = :userId " +
            "AND h.privacy = 'PUBLIC' " +
            "AND (r.privacy = 'PUBLIC' OR r.id IS NULL) " +
            "AND h.user.isDeleted = false " +
            "ORDER BY h.createdAt DESC")
    List<Object[]> findPublicHabitsAndRecordsByUserId(@Param("userId") Long userId);

    @Query("SELECT h, r FROM Habit h LEFT JOIN Record r ON h.id = r.habit.id " +
            "WHERE h.user.id = :userId " +
            "AND (h.privacy = 'FOLLOWERS' OR h.privacy = 'PUBLIC') " +
            "AND ((r.privacy = 'FOLLOWERS' OR r.privacy = 'PUBLIC') OR r.id IS NULL) " +
            "AND h.user.isDeleted = false")
    List<Object[]> findFollowHabitsAndRecordsByUserId(@Param("userId") Long userId);

    @Query("SELECT h FROM Habit h WHERE (h.title LIKE :keyword || '%' OR h.title LIKE '% ' || :keyword || '%') " +
            "AND h.user.isDeleted = false " +
            "AND h.id < :cursor AND h.privacy = 'PUBLIC' ORDER BY h.id DESC")
    Slice<Habit> searchHabitsByKeyword(@Param("keyword") String keyword,
                                       @Param("cursor") Long cursor,
                                       Pageable pageable);

    @Query("SELECT h FROM Habit h JOIN FETCH Follow f ON h.user.id = f.following.id " +
            "WHERE (h.title LIKE :keyword || '%' OR h.title LIKE '% ' || :keyword || '%') and h.id < :cursor " +
            "AND h.user.isDeleted = false " +
            "AND (h.privacy = 'PUBLIC' OR (f.follower.id = :loggedInUserId AND h.privacy != 'PRIVATE')) " +
            "ORDER BY h.id DESC")
    Slice<Habit> searchHabitsByKeywordForLoggedInUser(@Param("keyword") String keyword,
                                                      @Param("cursor") Long cursor,
                                                      @Param("loggedInUserId") Long loggedInUserId,
                                                      Pageable pageable);

    @Query("SELECT h FROM Habit h " +
            "WHERE FIND_IN_SET(:keyword, h.hashtags) > 0 and h.id < :cursor " +
            "AND h.user.isDeleted = false " +
            "AND (h.privacy = 'PUBLIC' " +
            "OR (h.user.id IN (SELECT f.following.id FROM Follow f WHERE f.follower.id = :loggedInUserId) AND h.privacy != 'PRIVATE')) " +
            "ORDER BY h.id DESC")
    Slice<Habit> searchByHashtagsForLoggedInUser(@Param("keyword") String keyword,
                                                 @Param("cursor") Long cursor,
                                                 @Param("loggedInUserId") Long loggedInUserId,
                                                 Pageable pageable);

    @Query("SELECT h FROM Habit h WHERE FIND_IN_SET(:keyword, h.hashtags) > 0 " +
            "AND h.user.isDeleted = false " +
            "AND h.privacy = 'PUBLIC' and h.id < :cursor ORDER BY h.id DESC")
    Slice<Habit> searchByHashtags(@Param("keyword") String keyword, @Param("cursor") Long cursor, Pageable pageable);


    @Query(nativeQuery = true, value = "SELECT h.* FROM habit h INNER JOIN user u ON h.user_id = u.id " +
            "WHERE h.privacy = 'PUBLIC' AND u.is_deleted = false ORDER BY RAND() LIMIT 4")
    List<Habit> findRandomHabits();


    @Query("SELECT DISTINCT h.title FROM Habit h WHERE h.title LIKE :keyword% ORDER BY LENGTH(h.title)")
    List<String> autoComplete(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT h FROM Habit h WHERE h.id < :cursor " +
            "AND h.privacy = 'PUBLIC' " +
            "AND h.user.isDeleted = false " +
            "AND h.createdAt > :oneDayAgo " +
            "ORDER BY h.id DESC")
    Page<Habit> findPublicNewHabit(@Param("cursor") Long cursor, @Param("oneDayAgo") LocalDateTime oneDayAgo, Pageable pageable);

    @Query("SELECT h FROM Habit h JOIN FETCH Follow f ON h.user.id = f.following.id " +
            "WHERE h.id < :cursor " +
            "AND h.user.isDeleted = false " +
            "AND (h.privacy = 'PUBLIC' OR (f.follower.id = :loggedInUserId AND h.privacy != 'PRIVATE')) " +
            "AND h.createdAt > :oneDayAgo " +
            "ORDER BY h.id DESC")
    Page<Habit> findFollowingNewHabit(@Param("cursor") Long cursor,
                                      @Param("loggedInUserId") Long loggedInUserId,
                                      @Param("oneDayAgo") LocalDateTime oneDayAgo,
                                      Pageable pageable);

    void deleteByUserId(Long id);

    List<Habit> findByUserIdOrderByCreatedAtDesc(Long userId);
}
