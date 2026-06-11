package org.exam.java.spring.asset_manager.repository;

import java.util.Optional;

import org.exam.java.spring.asset_manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
