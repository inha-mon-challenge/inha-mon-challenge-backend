package com.example.inhamonchallenge.domain.user.domain;

import com.example.inhamonchallenge.domain.common.BaseTime;
import com.example.inhamonchallenge.domain.common.Gender;
import com.example.inhamonchallenge.domain.common.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    private String introduction;

    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String profile;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean isPublic;

    private boolean isDeleted;

    private LocalDateTime deletedAt;

    @Builder
    public User(Long id, String name, String email, String password, String introduction, LocalDate birth, Gender gender,
                String profile, Role role, boolean isPublic, boolean isDeleted, LocalDateTime deletedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.introduction = introduction;
        this.birth = birth;
        this.gender = gender;
        this.profile = profile;
        this.role = role;
        this.isPublic = isPublic;
        this.isDeleted = isDeleted;
        this.deletedAt = deletedAt;
    }

    public void changePrivacy(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changePassword(PasswordEncoder encoder, String password) {
        this.password = encoder.encode(password);
    }

    public void changeDeletedStatus(boolean isDeleted) {
        this.isDeleted = isDeleted;
        this.deletedAt = isDeleted ? LocalDateTime.now() : null;
    }

    public void changeProfile(String profile) {
        this.profile = profile;
    }
}
