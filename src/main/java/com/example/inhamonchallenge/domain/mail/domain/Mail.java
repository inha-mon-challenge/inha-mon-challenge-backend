package com.example.inhamonchallenge.domain.mail.domain;

import com.example.inhamonchallenge.domain.common.BaseCreateTime;
import com.example.inhamonchallenge.domain.common.BaseTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mail extends BaseCreateTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String code;

    public Mail(String email, String code) {
        this.email = email;
        this.code = code;
    }

    public void updateAuthCode(String authCode) {
        this.code = authCode;
    }
}
