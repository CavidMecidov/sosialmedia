package com.sosialmedia.sosialmedia.repository;

import com.sosialmedia.sosialmedia.entity.File;
import com.sosialmedia.sosialmedia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<File,Long> {

    void deleteById(Long fileId);
    Optional<File>findById(Long fileId);
    File save(File file);
    Optional<File> findByUser(User user);
}
