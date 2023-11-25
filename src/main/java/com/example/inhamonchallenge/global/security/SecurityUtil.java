package com.example.inhamonchallenge.global.security;

import com.example.inhamonchallenge.domain.auth.exception.NotLoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class SecurityUtil {

    private SecurityUtil() {
    }

    public static Long getCurrentMemberId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName() == null || authentication.getName().equals("anonymousUser")) {
            throw new NotLoginException();
        }

        return Long.parseLong(authentication.getName());
    }

}
