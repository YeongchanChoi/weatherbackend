package com.audora.weatherapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.audora.weatherapp.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
