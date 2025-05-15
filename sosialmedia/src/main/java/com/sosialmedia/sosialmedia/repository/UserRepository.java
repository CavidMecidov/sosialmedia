package com.sosialmedia.sosialmedia.repository;

import com.sosialmedia.sosialmedia.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE " +
            "(:name IS NULL OR u.name LIKE %:name%) AND " +
            "(:surname IS NULL OR u.surname LIKE %:surname%) AND " +
            "(:username IS NULL OR u.username LIKE %:username%)")
    Page<User> searchUsers(@Param("name")String name,
                           @Param("surname") String surname,
                           @Param("username") String username,
                           Pageable pageable);
    List<User> findAll();
    User getByUserid(Long userid);
    Optional<User> findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long userid);


}
