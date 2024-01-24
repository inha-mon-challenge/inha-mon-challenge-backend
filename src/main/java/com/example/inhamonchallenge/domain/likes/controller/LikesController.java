package com.example.inhamonchallenge.domain.likes.controller;

import com.example.inhamonchallenge.domain.likes.dto.LikesRequest;
import com.example.inhamonchallenge.domain.likes.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
public class LikesController {

    private final LikesService likesService;

    @PostMapping
    ResponseEntity<Void> LikesAdd(@RequestBody LikesRequest request) {
        likesService.addLikes(request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{likesId}")
    ResponseEntity<Void> LikesDelete(@RequestBody LikesRequest request, @PathVariable Long likesId) {
        likesService.deleteLikes(request, likesId);
        return ResponseEntity.noContent().build();
    }
}
