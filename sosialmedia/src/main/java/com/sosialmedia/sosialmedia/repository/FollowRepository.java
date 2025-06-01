package com.sosialmedia.sosialmedia.repository;

import com.sosialmedia.sosialmedia.entity.Follow;
import com.sosialmedia.sosialmedia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;

public interface FollowRepository  extends JpaRepository<Follow,Long>{
    Optional<Follow> findByFollowerAndFollowing(User follower, User following);
    Optional<Follow>findByFollowerUseridAndFollowingUserid(Long userid,Long followingUserId);
    List<Follow> findByFollower(User user);
    List<Follow> findByFollowing(User user);
    @Query("SELECT f.following.id FROM Follow f WHERE f.follower.id = :userId")
    List<Long> findFollowedUserIds(@Param("userId") Long userId);
}
