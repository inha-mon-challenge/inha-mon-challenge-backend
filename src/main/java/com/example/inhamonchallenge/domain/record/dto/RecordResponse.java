package com.example.inhamonchallenge.domain.record.dto;

import com.example.inhamonchallenge.domain.record.domain.Record;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecordResponse {

    private Long userId;

    private Long recordId;

    private String content;

    private String image;

    private List<String> hashtag;

    private LocalDateTime createdAt;

    private int like;

    public static RecordResponse from(Record record) {
        return RecordResponse.builder()
                .userId(record.getUser().getId())
                .recordId(record.getId())
                .content(record.getContent())
                .image(record.getImage())
                .hashtag(Arrays.asList(record.getHashtags().split(",")))
                .createdAt(record.getCreatedAt())
                .like(record.getLikeCnt())
                .build();
    }

}
