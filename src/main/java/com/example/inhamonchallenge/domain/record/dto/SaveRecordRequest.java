package com.example.inhamonchallenge.domain.record.dto;

import com.example.inhamonchallenge.domain.habit.domain.Habit;
import com.example.inhamonchallenge.domain.record.domain.Record;
import com.example.inhamonchallenge.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@AllArgsConstructor
public class SaveRecordRequest {

    private String content;
    private MultipartFile image;
    private List<String> hashTag;

    public static Record toEntity(SaveRecordRequest request, Habit habit, User user, String imageUrl) {
        return Record.builder()
                .habit(habit)
                .image(imageUrl)
                .user(user)
                .content(request.getContent())
                .hashtags(String.join(",", request.getHashTag()))
                .reportCnt(0)
                .likeCnt(0)
                .build();
    }
}
