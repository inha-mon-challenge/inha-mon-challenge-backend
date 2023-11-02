package com.example.inhamonchallenge.domain.record.dto;

import com.example.inhamonchallenge.domain.record.domain.Record;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageRecordResponse {

    private String image;

    private LocalDateTime createdAt;

    public static ImageRecordResponse from(Record record) {
        return ImageRecordResponse.builder()
                .image(record.getImage())
                .createdAt(record.getCreatedDateTime())
                .build();
    }
}
