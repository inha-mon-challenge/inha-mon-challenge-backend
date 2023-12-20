package com.example.inhamonchallenge.domain.user.service;

import com.example.inhamonchallenge.domain.auth.repository.RefreshTokenRepository;
import com.example.inhamonchallenge.domain.user.dto.UserResponse;
import com.example.inhamonchallenge.domain.user.exception.ExistUsernameException;
import com.example.inhamonchallenge.domain.user.exception.InvalidPasswordException;
import com.example.inhamonchallenge.domain.user.exception.InvalidUsernameException;
import com.example.inhamonchallenge.domain.user.exception.NotFoundUserException;
import com.example.inhamonchallenge.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.inhamonchallenge.global.security.SecurityUtil.getCurrentMemberId;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse getUser(Long userId) {
        return UserResponse.from(userRepository.findById(userId).orElseThrow(NotFoundUserException::new));
    }

    public void checkNameDuplicate(String name) {
        userRepository.findByName(name).ifPresent(user -> {
            throw new ExistUsernameException();
        });
    }

    public void changeName(String name) {
        if (name.length() < 2 || name.length() > 10) {
            throw new InvalidUsernameException();
        }
        userRepository.findById(getCurrentMemberId()).ifPresent(user -> user.changeName(name));
    }

    public void verifyPassword(String password) {
        userRepository.findById(getCurrentMemberId()).ifPresent(user -> {
            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new InvalidPasswordException();
            }
        });
    }

    public void changePassword(String password) {
        userRepository.findById(getCurrentMemberId()).ifPresent(user -> user.changePassword(passwordEncoder, password));
    }

    public void changePrivacy(boolean isPublic) {
        userRepository.findById(getCurrentMemberId()).ifPresent(user -> user.changePrivacy(isPublic));
    }


    public void deleteUser() {
        userRepository.findById(getCurrentMemberId()).ifPresent(user -> user.changeDeletedStatus(true));
        refreshTokenRepository.deleteByKey(getCurrentMemberId().toString());
    }
}
