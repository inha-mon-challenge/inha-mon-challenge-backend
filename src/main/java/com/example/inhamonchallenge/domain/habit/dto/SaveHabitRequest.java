package com.example.inhamonchallenge.domain.habit.dto;

import com.example.inhamonchallenge.domain.common.Category;
import com.example.inhamonchallenge.domain.habit.domain.Habit;
import com.example.inhamonchallenge.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@AllArgsConstructor
public class SaveHabitRequest {

    private String content;
    private MultipartFile image;
    private String category;
    private List<String> hashTag;

    public static Habit toEntity(SaveHabitRequest request, User user, String imageUrl) {
        return Habit.builder()
                .user(user)
                .category(Category.valueOf(request.getCategory()))
                .content(request.getContent())
                .image(imageUrl)
                .hashtags(String.join(",", request.getHashTag()))
                .totalRecordCnt(0)
                .photoRecordCnt(0)
                .currentRecordCnt(0)
                .reportCnt(0)
                .likeCnt(0)
                .build();
    }
}
