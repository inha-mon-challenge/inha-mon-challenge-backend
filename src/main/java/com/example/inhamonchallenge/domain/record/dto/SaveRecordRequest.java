package com.example.inhamonchallenge.domain.record.dto;

import com.example.inhamonchallenge.domain.habit.domain.Habit;
import com.example.inhamonchallenge.domain.record.domain.Record;
import com.example.inhamonchallenge.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SaveRecordRequest {

    private String content;
    private String image;
    private List<String> hashTag;

    public static Record toEntity(SaveRecordRequest request, Habit habit, User user) {
        return Record.builder()
                .habit(habit)
                .image(request.image)
                .user(user)
                .content(request.getContent())
                .hashtags(String.join(",", request.getHashTag()))
                .reportCnt(0)
                .likeCnt(0)
                .privacy(habit.getPrivacy())
                .build();
    }
}
