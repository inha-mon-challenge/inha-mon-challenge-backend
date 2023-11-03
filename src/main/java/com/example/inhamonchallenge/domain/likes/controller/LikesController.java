package com.example.inhamonchallenge.domain.likes.controller;

import com.example.inhamonchallenge.domain.likes.dto.SaveLikesRequest;
import com.example.inhamonchallenge.domain.likes.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
public class LikesController {

    private final LikesService likesService;

    @PostMapping
    ResponseEntity<Void> LikesAdd(@RequestBody SaveLikesRequest request) {
        likesService.addLikes(request);
        return ResponseEntity.noContent().build();
    }
}
