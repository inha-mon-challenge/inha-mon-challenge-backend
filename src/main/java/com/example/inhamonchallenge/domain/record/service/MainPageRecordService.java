package com.example.inhamonchallenge.domain.record.service;

import com.example.inhamonchallenge.domain.common.dto.Result;
import com.example.inhamonchallenge.domain.record.domain.Record;
import com.example.inhamonchallenge.domain.record.dto.RecordResponse;
import com.example.inhamonchallenge.domain.record.repository.RecordRepository;
import com.example.inhamonchallenge.global.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.inhamonchallenge.global.security.SecurityUtil.*;

@Service
@Transactional
@RequiredArgsConstructor
public class MainPageRecordService {

    private final RecordRepository recordRepository;

    public Result<List<RecordResponse>> getFollowingRecords(Long cursor) {
        if(cursor == null) {
            cursor = Long.MAX_VALUE;
        }
        Pageable pageable = PageRequest.of(0, 4, Sort.by(Sort.Direction.DESC, "id"));
        Slice<Record> followingRecords = recordRepository.findFollowingTop4(getCurrentMemberId(), cursor, pageable);
        return new Result<>(followingRecords.getContent()
                .stream()
                .map(RecordResponse::from)
                .collect(Collectors.toList()));
    }

    public Result<List<RecordResponse>> getNonFollowingRecords(Long cursor, int count) {
        if (cursor == null) {
            cursor = Long.MAX_VALUE;
        }

        Pageable pageable = PageRequest.of(0, count, Sort.by(Sort.Direction.DESC, "id"));
        Slice<Record> nonFollowingRecords = recordRepository.findNonFollowingTop(getCurrentMemberId(), cursor, pageable);

        return new Result<>(nonFollowingRecords.getContent()
                .stream()
                .map(RecordResponse::from)
                .collect(Collectors.toList()));
    }

    public Result<List<RecordResponse>> getPublicRecords(Long cursor) {
        if (cursor == null) {
            cursor = Long.MAX_VALUE;
        }

        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"));
        Slice<Record> publicRecords = recordRepository.findPublicTop10(cursor, pageable);

        return new Result<>(publicRecords.getContent()
                .stream()
                .map(RecordResponse::from)
                .collect(Collectors.toList()));
    }
}
