package com.sosialmedia.sosialmedia.repository;

import com.sosialmedia.sosialmedia.entity.Follow;
import com.sosialmedia.sosialmedia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface FollowRepository  extends JpaRepository<Follow,Long>{
    Optional<Follow> findByFollowerAndFollowing(User follower, User following);
    List<Follow> findByFollower(User user);
    List<Follow> findByFollowing(User user);
}
