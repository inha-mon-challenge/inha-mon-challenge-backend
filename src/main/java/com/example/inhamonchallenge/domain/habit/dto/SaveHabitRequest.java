package com.example.inhamonchallenge.domain.habit.dto;

import com.example.inhamonchallenge.domain.common.Category;
import com.example.inhamonchallenge.domain.common.PrivacySetting;
import com.example.inhamonchallenge.domain.habit.domain.Frequency;
import com.example.inhamonchallenge.domain.habit.domain.Habit;
import com.example.inhamonchallenge.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@AllArgsConstructor
public class SaveHabitRequest {

    private String title;
    private String content;
    private String category;
    private String frequency;
    private List<String> hashTag;

    public static Habit toEntity(SaveHabitRequest request, User user) {
        return Habit.builder()
                .user(user)
                .category(Category.valueOf(request.getCategory()))
                .content(request.getContent())
                .title(request.getTitle())
                .frequency(Frequency.fromString(request.getFrequency()))
                .hashtags(String.join(",", request.getHashTag()))
                .totalRecordCnt(0)
                .photoRecordCnt(0)
                .currentRecordCnt(0)
                .reportCnt(0)
                .likeCnt(0)
                .privacy(PrivacySetting.PUBLIC)
                .build();
    }
}
