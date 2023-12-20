package com.example.inhamonchallenge.domain.user.service;

import com.example.inhamonchallenge.domain.comment.repository.CommentRepository;
import com.example.inhamonchallenge.domain.follow.repository.FollowRepository;
import com.example.inhamonchallenge.domain.habit.repository.HabitRepository;
import com.example.inhamonchallenge.domain.likes.repository.LikesRepository;
import com.example.inhamonchallenge.domain.record.repository.RecordRepository;
import com.example.inhamonchallenge.domain.user.domain.User;
import com.example.inhamonchallenge.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSchedulerService {

    private final UserRepository userRepository;
    private final HabitRepository habitRepository;
    private final RecordRepository recordRepository;
    private final FollowRepository followRepository;
    private final CommentRepository commentRepository;
    private final LikesRepository likesRepository;

    @Scheduled(cron = "0 0 0  * * *")
    public void run() {
        LocalDateTime threeMonthsAgo = LocalDateTime.now().minusMonths(3);
        List<User> users = userRepository.findDeletedUsersOlderThanThreeMonths(threeMonthsAgo);
        users.stream().forEach(user -> {
            habitRepository.deleteByUserId(user.getId());
            recordRepository.deleteByUserId(user.getId());
            followRepository.deleteByFollowerIdOrFollowingId(user.getId(), user.getId());
            commentRepository.deleteByUserId(user.getId());
            likesRepository.deleteByUserId(user.getId());
            userRepository.delete(user);
        });
    }
}
