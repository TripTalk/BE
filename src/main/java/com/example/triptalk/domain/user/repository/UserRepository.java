package com.example.triptalk.domain.user.repository;

import com.example.triptalk.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // email로 유저 조회
    Optional<User> findByEmail(String email);

    // email 중복 체크
    boolean existsByEmail(String email);
}

