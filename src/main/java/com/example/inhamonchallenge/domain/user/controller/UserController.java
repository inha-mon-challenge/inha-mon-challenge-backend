package com.example.inhamonchallenge.domain.user.controller;

import com.example.inhamonchallenge.domain.user.dto.PasswordRequest;
import com.example.inhamonchallenge.domain.user.dto.UserIdResponse;
import com.example.inhamonchallenge.domain.user.dto.UserResponse;
import com.example.inhamonchallenge.domain.user.service.UserService;
import com.example.inhamonchallenge.global.security.SecurityUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.example.inhamonchallenge.global.security.SecurityUtil.*;

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

    @GetMapping("/check-name")
    ResponseEntity<Void> checkNameDuplicate(@RequestParam String name) {
        userService.checkNameDuplicate(name);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/current")
    ResponseEntity<UserIdResponse> getCurrentUser() {
        return ResponseEntity.ok(UserIdResponse.from(getCurrentMemberId()));
    }

    @PostMapping("/change-name")
    ResponseEntity<Void> changeName(@RequestParam String name) {
        userService.changeName(name);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/verify-password")
    ResponseEntity<Void> verifyPassword(@Valid @RequestBody PasswordRequest request) {
        userService.verifyPassword(request.getPassword());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/change-password")
    ResponseEntity<Void> changePassword(@Valid @RequestBody PasswordRequest request) {
        userService.changePassword(request.getPassword());
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/privacy")
    ResponseEntity<Void> changePrivacy(@RequestParam boolean isPublic) {
        userService.changePrivacy(isPublic);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/change-profile")
    ResponseEntity<Void> changeProfile(@RequestParam String profile) {
        userService.changeProfile(profile);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/change-introduction")
    ResponseEntity<Void> changeIntroduction(@RequestParam String introduction) {
        userService.changeIntroduction(introduction);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    ResponseEntity<Void> deleteUser() {
        userService.deleteUser();
        return ResponseEntity.noContent().build();
    }

}
