package com.sosialmedia.sosialmedia.repository;

import com.sosialmedia.sosialmedia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Optional<User> findByEmail(String email);


}
