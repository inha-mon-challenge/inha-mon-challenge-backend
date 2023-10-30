package com.example.inhamonchallenge.domain.user.repository;

import com.example.inhamonchallenge.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
