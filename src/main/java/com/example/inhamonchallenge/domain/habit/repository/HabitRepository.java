package com.example.inhamonchallenge.domain.habit.repository;

import com.example.inhamonchallenge.domain.habit.domain.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitRepository extends JpaRepository<Habit, Long> {

    List<Habit> findAllByUserId(Long userId);
}
