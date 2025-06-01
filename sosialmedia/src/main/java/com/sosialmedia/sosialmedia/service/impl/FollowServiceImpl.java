package com.sosialmedia.sosialmedia.service.impl;

import com.sosialmedia.sosialmedia.dto.FollowResponse;
import com.sosialmedia.sosialmedia.entity.Follow;
import com.sosialmedia.sosialmedia.entity.User;
import com.sosialmedia.sosialmedia.exception.ConflictException;
import com.sosialmedia.sosialmedia.exception.NotFoundException;
import com.sosialmedia.sosialmedia.mapper.FollowMapper;
import com.sosialmedia.sosialmedia.repository.FollowRepository;
import com.sosialmedia.sosialmedia.repository.UserRepository;
import com.sosialmedia.sosialmedia.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final FollowMapper followMapper;

    @Override
    public void followUser(Long followerId, Long followingId) {
        if(followerId.equals(followingId)){
            throw new ConflictException("You can't follow yourself");
        }
        User follower =  userRepository.findById(followerId)
                .orElseThrow(() -> new NotFoundException("Follower user not found"));
        User following = userRepository.findById(followingId)
                .orElseThrow(() -> new NotFoundException("Following user not found"));

        Optional<Follow> existFollow= followRepository.findByFollowerUseridAndFollowingUserid(followerId,followingId);
        if(existFollow.isPresent()){
            throw  new ConflictException("You are already following this user");
        }
        Follow follow =  new Follow();
        follow.setFollower(follower);
        follow.setFollowing(following);
        follow.setFollowAt(LocalDateTime.now());
        followRepository.save(follow);


    }

    @Override
    public void unFollowUser(Long followerId, Long followingId) {
        User follower =  userRepository.findById(followerId)
                .orElseThrow(() -> new RuntimeException("Follower user not found"));
        User following = userRepository.findById(followingId)
                .orElseThrow(() -> new RuntimeException("Following user not found"));
        Follow follow  = followRepository.findByFollowerAndFollowing(follower,following)
                .orElseThrow(() -> new RuntimeException("You are not following this user"));

               followRepository.delete(follow);
        System.out.println("User deleted successful : " + follow);
    }

    @Override
    public List<FollowResponse> getFollowers(Long userid) {
        User user = userRepository.findById(userid)
                .orElseThrow(() -> new NotFoundException("User not found"));
        List<Follow> followers = followRepository.findByFollowing(user);
        return followers.stream()
                .map(follow -> followMapper.toFollowResponse(follow.getFollower()) )
                .collect(Collectors.toList());
    }

    @Override
    public List<FollowResponse> getFollowing(Long userid) {
     User user =  userRepository.findById(userid)
             .orElseThrow(() -> new NotFoundException("User not found"));

     List<Follow> following =  followRepository.findByFollower(user);
        return following.stream()
                .map(follow -> followMapper.toFollowResponse(follow.getFollowing()))
                .collect(Collectors.toList());
    }
}
