package com.example.Homework_3.repository;

import com.example.Homework_3.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String username);

    boolean existsByEmail(String adminEmail);
}
