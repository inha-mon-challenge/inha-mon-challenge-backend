package com.example.inhamonchallenge.domain.user.repository;

import com.example.inhamonchallenge.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.isDeleted = false")
    boolean existsByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.isDeleted = false")
    Optional<User> findByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.name = :name AND u.isDeleted = false")
    Optional<User> findByName(@Param("name") String name);

    @Query("SELECT u FROM User u WHERE u.isDeleted = true AND u.deletedAt < :threeMonthsAgo")
    List<User> findDeletedUsersOlderThanThreeMonths(@Param("threeMonthsAgo") LocalDateTime threeMonthsAgo);

    @Query("SELECT u.isDeleted FROM User u WHERE u.id = :id")
    Optional<Boolean> isUserDeleted(@Param("id") Long id);
}
