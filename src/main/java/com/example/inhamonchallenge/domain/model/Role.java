package com.example.inhamonchallenge.domain.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {

    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    private final String role;


}
