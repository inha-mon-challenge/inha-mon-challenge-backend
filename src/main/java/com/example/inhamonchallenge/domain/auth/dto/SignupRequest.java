package com.example.inhamonchallenge.domain.auth.dto;

import com.example.inhamonchallenge.domain.common.Gender;
import com.example.inhamonchallenge.domain.common.Role;
import com.example.inhamonchallenge.domain.user.domain.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SignupRequest {
    @NotBlank(message = "이메일 입력은 필수입니다.")
    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@(inha.edu|inha.ac.kr)$", message = "잘못된 형식이거나 허용되지 않는 이메일입니다.")
    private String email;


    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*()_+=-])[a-zA-Z0-9!@#$%^&*()_+=-]{8,}$", message = "비밀번호는 8자 이상, 영문, 숫자, 특수문자를 포함해야 합니다.")
    private String password;

    @NotBlank(message = "이름 입력은 필수입니다.")
    private String name;
    private LocalDate birth;
    private String gender;

    public User toEntity(PasswordEncoder passwordEncoder){
        return User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .name(name)
                .birth(birth)
                .gender(Gender.valueOf(gender.toUpperCase()))
                .role(Role.ROLE_USER)
                .isPublic(true)
                .isDeleted(false)
                .build();
    }
}
