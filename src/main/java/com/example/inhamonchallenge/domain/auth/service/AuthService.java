package com.example.inhamonchallenge.domain.auth.service;

import com.example.inhamonchallenge.domain.auth.dto.SignupRequest;
import com.example.inhamonchallenge.domain.auth.dto.SignupResponse;
import com.example.inhamonchallenge.domain.user.domain.User;
import com.example.inhamonchallenge.domain.user.repository.UserRepository;
import com.example.inhamonchallenge.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.inhamonchallenge.global.error.ErrorCode.*;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignupResponse signup(SignupRequest request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException(EXIST_EMAIL_EXCEPTION.getMessage());
        }
        User user = request.toEntity(passwordEncoder);
        return SignupResponse.from(userRepository.save(user));
    }
}
