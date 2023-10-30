package com.example.inhamonchallenge.domain.record.repository;

import com.example.inhamonchallenge.domain.record.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {
}
