package com.example.inhamonchallenge.domain.auth.service;

import com.example.inhamonchallenge.domain.auth.domain.RefreshToken;
import com.example.inhamonchallenge.domain.auth.dto.LoginRequest;
import com.example.inhamonchallenge.domain.auth.dto.SignupRequest;
import com.example.inhamonchallenge.domain.auth.dto.SignupResponse;
import com.example.inhamonchallenge.domain.auth.dto.TokenRequest;
import com.example.inhamonchallenge.domain.auth.exception.InvalidRefreshTokenException;
import com.example.inhamonchallenge.domain.auth.exception.LogoutUserException;
import com.example.inhamonchallenge.domain.auth.repository.RefreshTokenRepository;
import com.example.inhamonchallenge.domain.user.domain.User;
import com.example.inhamonchallenge.domain.user.repository.UserRepository;
import com.example.inhamonchallenge.global.jwt.TokenDto;
import com.example.inhamonchallenge.global.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.inhamonchallenge.global.error.ErrorCode.EXIST_EMAIL_EXCEPTION;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public SignupResponse signup(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException(EXIST_EMAIL_EXCEPTION.getMessage());
        }
        User user = request.toEntity(passwordEncoder);
        return SignupResponse.from(userRepository.save(user));
    }

    public TokenDto login(LoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = request.toAuthentication();

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        if(refreshTokenRepository.existsByKey(authentication.getName())) {
            refreshTokenRepository.deleteByKey(authentication.getName());
        }
        refreshTokenRepository.save(refreshToken);

        return tokenDto;
    }

    public TokenDto reissue(TokenRequest request) {
        if (!tokenProvider.validateToken(request.getRefreshToken())) {
            throw new InvalidRefreshTokenException();
        }

        Authentication authentication = tokenProvider.getAuthentication(request.getAccessToken());

        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(LogoutUserException::new);

        if (!refreshToken.getValue().equals(request.getRefreshToken())) {
            throw new InvalidRefreshTokenException();
        }

        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        refreshToken.updateValue(tokenDto.getRefreshToken());

        return tokenDto;
    }
}
