package com.example.inhamonchallenge.domain.habit.repository;

import com.example.inhamonchallenge.domain.habit.domain.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HabitRepository extends JpaRepository<Habit, Long> {

    @Query("SELECT h, r FROM Habit h JOIN FETCH Record r ON h.id = r.habit.id WHERE h.user.id = :userId")
    List<Object[]> findHabitsAndRecordsByUserId(@Param("userId") Long userId);

    @Query(nativeQuery = true, value = "SELECT * FROM habit ORDER BY RAND() LIMIT 4")
    List<Habit> findRandomHabits();

}
