package com.example.inhamonchallenge.domain.hashtag.repository;

import com.example.inhamonchallenge.domain.hashtag.domain.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HashTagRepository extends JpaRepository<HashTag, Long> {

    List<String> findAllByHabitId(Long habitId);
}
