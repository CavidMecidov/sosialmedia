package com.sosialmedia.sosialmedia.service;

import com.sosialmedia.sosialmedia.dto.FollowResponse;
import com.sosialmedia.sosialmedia.entity.User;
import java.util.List;
public interface FollowService {
    void followUser(Long followerId,Long followingId);
    void unFollowUser(Long followerId,Long followingId);
    List<FollowResponse>getFollowers(Long userid);
    List<FollowResponse>getFollowing(Long userid);
}
