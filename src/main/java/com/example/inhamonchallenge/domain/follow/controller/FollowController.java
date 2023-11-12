package com.example.inhamonchallenge.domain.follow.controller;

import com.example.inhamonchallenge.domain.common.dto.Result;
import com.example.inhamonchallenge.domain.follow.dto.FollowRequestResponse;
import com.example.inhamonchallenge.domain.follow.dto.FollowResponse;
import com.example.inhamonchallenge.domain.follow.dto.FollowingUserResponse;
import com.example.inhamonchallenge.domain.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/follow")
public class FollowController {

    private final FollowService followService;

    @GetMapping("/{userId}/following")
    public ResponseEntity<Result<List<FollowingUserResponse>>> followingList(@PathVariable Long userId){
        Result<List<FollowingUserResponse>> response = followService.getFollowings(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}/follower")
    public ResponseEntity<Result<List<FollowingUserResponse>>> followerList(@PathVariable Long userId){
        Result<List<FollowingUserResponse>> response = followService.getFollowers(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/request")
    public ResponseEntity<Result<List<FollowRequestResponse>>> requestList(){
        Result<List<FollowRequestResponse>> response = followService.getRequestList();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<FollowResponse> FollowRequest(@PathVariable Long userId){
        FollowResponse response = followService.requestFollow(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{followId}")
    public ResponseEntity<FollowResponse> FollowAccept(@PathVariable Long followId){
        FollowResponse response = followService.acceptFollow(followId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{followId}")
    public ResponseEntity<Void> FollowDelete(@PathVariable Long followId){
        followService.deleteFollow(followId);
        return ResponseEntity.noContent().build();
    }
}
