package com.example.inhamonchallenge.domain.auth.repository;

import com.example.inhamonchallenge.domain.auth.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    Optional<RefreshToken> findByKey(String key);

    boolean existsByKey(String name);

    void deleteByKey(String name);
}
