package com.example.inhamonchallenge.domain.user.domain;

import com.example.inhamonchallenge.domain.common.BaseTime;
import com.example.inhamonchallenge.domain.common.Role;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class User extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private LocalDateTime birth;

    private Character gender;

    private String profile;

    private boolean is_verified;

    @Enumerated(EnumType.STRING)
    private Role role;
}
