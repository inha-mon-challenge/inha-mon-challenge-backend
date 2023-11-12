package com.example.inhamonchallenge.domain.follow.controller;

import com.example.inhamonchallenge.domain.follow.dto.FollowResponse;
import com.example.inhamonchallenge.domain.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/follow")
public class FollowController {

    private final FollowService followService;

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
