package com.example.inhamonchallenge.domain.user.domain;

import com.example.inhamonchallenge.domain.common.BaseTime;
import com.example.inhamonchallenge.domain.common.Gender;
import com.example.inhamonchallenge.domain.common.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String profile;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(Long id, String name, String email, String password, LocalDate birth, Gender gender, String profile, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.birth = birth;
        this.gender = gender;
        this.profile = profile;
        this.role = role;
    }
}
