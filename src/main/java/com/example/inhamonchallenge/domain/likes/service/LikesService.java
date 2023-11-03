package com.example.inhamonchallenge.domain.likes.service;

import com.example.inhamonchallenge.domain.common.FeedType;
import com.example.inhamonchallenge.domain.common.exception.NotFoundFeedException;
import com.example.inhamonchallenge.domain.habit.repository.HabitRepository;
import com.example.inhamonchallenge.domain.likes.domain.Likes;
import com.example.inhamonchallenge.domain.likes.dto.LikesRequest;
import com.example.inhamonchallenge.domain.likes.exception.ExistsLikeException;
import com.example.inhamonchallenge.domain.likes.exception.NotFoundLikesException;
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

    public void addLikes(LikesRequest request) {
        User user = userRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(NotFoundUserException::new);
        if (!isValidFeed(request.getFeedType(), request.getFeedId())) {
            throw new NotFoundFeedException();
        }
        if(likesRepository.existsByFeedIdAndFeedTypeAndUserId(request.getFeedId(), request.getFeedType(), user.getId())){
            throw new ExistsLikeException();
        }
        updateLikeCnt(request.getFeedType(), request.getFeedId(), true);
        likesRepository.save(request.toEntity(request, user));
    }

    public void deleteLikes(LikesRequest request, Long likesId){
        User user = userRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(NotFoundUserException::new);
        if (!isValidFeed(request.getFeedType(), request.getFeedId())) {
            throw new NotFoundFeedException();
        }
        Likes likes = likesRepository.findById(likesId).orElseThrow(NotFoundLikesException::new);
        updateLikeCnt(request.getFeedType(), request.getFeedId(), false);
        likesRepository.delete(likes);
    }

    private void updateLikeCnt(FeedType feedType, Long feedId, boolean isIncrease) {
        if (feedType == FeedType.HABIT && isIncrease) {
            likesRepository.increaseHabitLikes(feedId);
        } else if (feedType == FeedType.RECORD && isIncrease) {
            likesRepository.increaseRecordLikes(feedId);
        }
        else if (feedType == FeedType.HABIT && !isIncrease) {
            likesRepository.decreaseHabitLikes(feedId);
        } else if (feedType == FeedType.RECORD && !isIncrease) {
            likesRepository.decreaseRecordLikes(feedId);
        }
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
