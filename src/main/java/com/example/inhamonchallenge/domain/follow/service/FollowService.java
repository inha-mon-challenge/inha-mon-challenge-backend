package com.example.inhamonchallenge.domain.follow.service;

import com.example.inhamonchallenge.domain.follow.controller.FollowStatus;
import com.example.inhamonchallenge.domain.follow.domain.Follow;
import com.example.inhamonchallenge.domain.follow.dto.FollowResponse;
import com.example.inhamonchallenge.domain.follow.exception.ExistFollowException;
import com.example.inhamonchallenge.domain.follow.repository.FollowRepository;
import com.example.inhamonchallenge.domain.user.domain.User;
import com.example.inhamonchallenge.domain.user.exception.NotFoundUserException;
import com.example.inhamonchallenge.domain.user.repository.UserRepository;
import com.example.inhamonchallenge.global.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.inhamonchallenge.global.security.SecurityUtil.*;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public FollowResponse requestFollow(Long userId){
        Long currentMemberId = getCurrentMemberId();

        if (followRepository.existsByFollowerIdAndFollowingId(currentMemberId, userId)) {
            throw new ExistFollowException();
        }

        User follower = userRepository.findById(currentMemberId)
                .orElseThrow(NotFoundUserException::new);
        User following = userRepository.findById(userId)
                .orElseThrow(NotFoundUserException::new);

        Follow follow = Follow.builder()
                .follower(follower)
                .following(following)
                .status(FollowStatus.PENDING)
                .build();

        return FollowResponse.from(followRepository.save(follow).getId(), currentMemberId, userId);
    }
}
