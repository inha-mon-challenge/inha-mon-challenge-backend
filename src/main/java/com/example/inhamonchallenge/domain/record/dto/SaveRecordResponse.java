package com.example.inhamonchallenge.domain.record.dto;

import com.example.inhamonchallenge.domain.common.Category;
import com.example.inhamonchallenge.domain.habit.domain.Habit;
import com.example.inhamonchallenge.domain.record.domain.Record;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveRecordResponse {

    private Long recordId;

    private String content;

    private String image;

    private List<String> hashtag;

    private LocalDateTime createdAt;

    public static SaveRecordResponse from(Record record) {
        return SaveRecordResponse.builder()
                .recordId(record.getId())
                .content(record.getContent())
                .image(record.getImage())
                .hashtag(Arrays.asList(record.getHashtags().split(",")))
                .createdAt(record.getCreatedDateTime())
                .build();
    }
}
