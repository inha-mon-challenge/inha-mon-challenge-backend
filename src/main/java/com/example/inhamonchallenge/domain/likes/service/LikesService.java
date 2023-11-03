package com.example.inhamonchallenge.domain.likes.service;

import com.example.inhamonchallenge.domain.common.FeedType;
import com.example.inhamonchallenge.domain.common.exception.NotFoundFeedException;
import com.example.inhamonchallenge.domain.habit.repository.HabitRepository;
import com.example.inhamonchallenge.domain.likes.dto.SaveLikesRequest;
import com.example.inhamonchallenge.domain.likes.repository.LikesRepository;
import com.example.inhamonchallenge.domain.record.repository.RecordRepository;
import com.example.inhamonchallenge.domain.user.domain.User;
import com.example.inhamonchallenge.domain.user.exception.NotFoundUserException;
import com.example.inhamonchallenge.domain.user.repository.UserRepository;
import com.example.inhamonchallenge.global.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LikesService {

    private final LikesRepository likesRepository;
    private final UserRepository userRepository;
    private final HabitRepository habitRepository;
    private final RecordRepository recordRepository;

    public void addLikes(SaveLikesRequest request) {
        User user = userRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(NotFoundUserException::new);
        if (!isValidFeed(request.getFeedType(), request.getFeedId())) {
            throw new NotFoundFeedException();
        }
        likesRepository.save(request.toEntity(request, user));
    }

    private boolean isValidFeed(FeedType feedType, Long feedId) {
        if (feedType == FeedType.HABIT) {
            return habitRepository.existsById(feedId);
        } else if (feedType == FeedType.RECORD) {
            return recordRepository.existsById(feedId);
        }
        return false;
    }
}
