package com.example.inhamonchallenge.domain.follow.service;

import com.example.inhamonchallenge.domain.common.dto.Result;
import com.example.inhamonchallenge.domain.follow.controller.FollowStatus;
import com.example.inhamonchallenge.domain.follow.domain.Follow;
import com.example.inhamonchallenge.domain.follow.dto.FollowRequestResponse;
import com.example.inhamonchallenge.domain.follow.dto.FollowResponse;
import com.example.inhamonchallenge.domain.follow.dto.FollowingUserResponse;
import com.example.inhamonchallenge.domain.follow.exception.ExistFollowException;
import com.example.inhamonchallenge.domain.follow.exception.NotFoundFollowException;
import com.example.inhamonchallenge.domain.follow.repository.FollowRepository;
import com.example.inhamonchallenge.domain.user.domain.User;
import com.example.inhamonchallenge.domain.user.exception.NotFoundUserException;
import com.example.inhamonchallenge.domain.user.repository.UserRepository;
import com.example.inhamonchallenge.global.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.inhamonchallenge.global.security.SecurityUtil.*;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public Result<List<FollowingUserResponse>> getFollowings(Long userId) {
        userRepository.findById(userId).orElseThrow(NotFoundUserException::new);
        List<User> followings = followRepository.findAcceptedFollowingByUserId(userId);
        List<FollowingUserResponse> response = followings.stream()
                .map(FollowingUserResponse::from)
                .collect(Collectors.toList());
        return new Result<>(response);
    }


    public Result<List<FollowingUserResponse>> getFollowers(Long userId) {
        userRepository.findById(userId).orElseThrow(NotFoundUserException::new);
        List<User> followers = followRepository.findAllByFollowingId(userId);
        List<FollowingUserResponse> response = followers.stream()
                .map(FollowingUserResponse::from)
                .collect(Collectors.toList());
        return new Result<>(response);
    }

    public Result<List<FollowRequestResponse>> getRequestList() {
        Long currentMemberId = getCurrentMemberId();
        List<Follow> followRequests = followRepository.findAllByFollowingIdAndStatus(currentMemberId);
        List<FollowRequestResponse> response = followRequests.stream()
                .map(follow -> FollowRequestResponse.from(follow.getId(), follow.getFollower()))
                .collect(Collectors.toList());
        return new Result<>(response);
    }

    public FollowResponse requestFollow(Long userId) {
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

    public FollowResponse acceptFollow(Long followId) {
        Follow follow = followRepository.findById(followId)
                .orElseThrow(NotFoundFollowException::new);

        follow.acceptFollow();

        return FollowResponse.from(follow.getId(), follow.getFollower().getId(), follow.getFollowing().getId());
    }

    public void deleteFollow(Long followId) {
        Follow follow = followRepository.findById(followId)
                .orElseThrow(NotFoundFollowException::new);

        followRepository.delete(follow);
    }


}
