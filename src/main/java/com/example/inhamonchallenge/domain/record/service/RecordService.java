package com.example.inhamonchallenge.domain.record.service;

import com.example.inhamonchallenge.domain.record.domain.Record;
import com.example.inhamonchallenge.domain.record.dto.RecordResponse;
import com.example.inhamonchallenge.domain.record.exception.NotFoundRecordException;
import com.example.inhamonchallenge.domain.record.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;

    public RecordResponse getRecord(Long recordId) {
        Record record = recordRepository.findById(recordId).orElseThrow(NotFoundRecordException::new);
        return RecordResponse.from(record);
    }
}
