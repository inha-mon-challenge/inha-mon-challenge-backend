package com.example.inhamonchallenge.domain.habit.repository;

import com.example.inhamonchallenge.domain.habit.domain.Habit;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HabitRepository extends JpaRepository<Habit, Long> {

    @Query("SELECT h, r FROM Habit h JOIN FETCH Record r ON h.id = r.habit.id WHERE h.user.id = :userId")
    List<Object[]> findHabitsAndRecordsByUserId(@Param("userId") Long userId);

    @Query("SELECT h FROM Habit h WHERE (h.title LIKE :keyword || '%' OR h.title LIKE '% ' || :keyword || '%')" +
            " and h.id < :cursor and h.privacy = 'PUBLIC' ORDER BY h.id DESC")
    Slice<Habit> searchHabitsByKeyword(@Param("keyword") String keyword,
                                       @Param("cursor") Long cursor,
                                       Pageable pageable);

    @Query("SELECT h FROM Habit h JOIN FETCH Follow f ON h.user.id = f.following.id " +
            "WHERE (h.title LIKE :keyword || '%' OR h.title LIKE '% ' || :keyword || '%') and h.id < :cursor " +
            "AND (h.privacy = 'PUBLIC' OR (f.follower.id = :loggedInUserId AND h.privacy != 'PRIVATE')) " +
            "ORDER BY h.id DESC")
    Slice<Habit> searchHabitsByKeywordForLoggedInUser(@Param("keyword") String keyword,
                                                      @Param("cursor") Long cursor,
                                                      @Param("loggedInUserId") Long loggedInUserId,
                                                      Pageable pageable);

    @Query(value = "SELECT h FROM Habit h WHERE FIND_IN_SET(:keyword, h.hashtags) > 0 and h.id < :cursor ORDER BY h.id DESC")
    Slice<Habit> searchByHashtags(@Param("keyword") String keyword, @Param("cursor") Long cursor, Pageable pageable);
    
    @Query(nativeQuery = true, value = "SELECT * FROM habit WHERE privacy = 'PUBLIC' ORDER BY RAND() LIMIT 4")
    List<Habit> findRandomHabits();



}
