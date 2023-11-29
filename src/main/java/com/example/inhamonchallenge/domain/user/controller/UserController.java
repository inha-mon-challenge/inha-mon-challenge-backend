package com.example.inhamonchallenge.domain.user.controller;

import com.example.inhamonchallenge.domain.user.dto.UserResponse;
import com.example.inhamonchallenge.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    ResponseEntity<UserResponse> userDetail(@PathVariable Long userId) {
        UserResponse response = userService.getUser(userId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/privacy")
    ResponseEntity<Void> changePrivacy(@RequestParam boolean isPublic) {
        userService.changePrivacy(isPublic);
        return ResponseEntity.noContent().build();
    }
}
