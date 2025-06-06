package com.sosialmedia.sosialmedia.repository;

import com.sosialmedia.sosialmedia.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserId(Long userid);
    List<Post>findAllByOrderByCreatedDateDesc();
    List<Post> findByUserIdInOrderByCreatedDateDesc(List<Long> userIds);
}
