package com.example.inhamonchallenge.domain.auth.controller;

import com.example.inhamonchallenge.domain.auth.dto.LoginRequest;
import com.example.inhamonchallenge.domain.auth.dto.SignupRequest;
import com.example.inhamonchallenge.domain.auth.dto.SignupResponse;
import com.example.inhamonchallenge.domain.auth.dto.TokenRequest;
import com.example.inhamonchallenge.domain.auth.service.AuthService;
import com.example.inhamonchallenge.global.jwt.TokenDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@Valid @RequestBody SignupRequest request) {
        return ResponseEntity.status(CREATED).body(authService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequest tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }
}
