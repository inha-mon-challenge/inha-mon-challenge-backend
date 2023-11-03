package com.example.inhamonchallenge.domain.comment.controller;

import com.example.inhamonchallenge.domain.comment.dto.SaveCommentRequest;
import com.example.inhamonchallenge.domain.comment.dto.SaveCommentResponse;
import com.example.inhamonchallenge.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    ResponseEntity<SaveCommentResponse> commentAdd(@RequestBody SaveCommentRequest request) {
        return ResponseEntity.status(CREATED).body(commentService.addComment(request));
    }


}
