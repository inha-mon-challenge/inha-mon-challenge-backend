package com.example.inhamonchallenge.domain.follow.controller;

import com.example.inhamonchallenge.domain.follow.dto.FollowResponse;
import com.example.inhamonchallenge.domain.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
