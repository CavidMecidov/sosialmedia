package com.sosialmedia.sosialmedia.controller;

import com.sosialmedia.sosialmedia.dto.FollowResponse;
import com.sosialmedia.sosialmedia.entity.User;
import com.sosialmedia.sosialmedia.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("api/v1/user_follows")
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;

    @PostMapping("/follow/{followingId}")
    public ResponseEntity<String> followUser(@AuthenticationPrincipal User user,
                                             @PathVariable Long followingId) {
        followService.followUser(user.getId(), followingId);
        return ResponseEntity.ok("Successfully followed the user");

    }
    @DeleteMapping("//unfollow/{followingId}")
    public ResponseEntity<String> unFollow(@AuthenticationPrincipal User user,
                                           @PathVariable Long followingId){
        followService.unFollowUser(user.getId(), followingId);
        return ResponseEntity.ok("Successfully unfollow the user ");
    }
    @GetMapping("/{userid}/follower")
    public ResponseEntity<List<FollowResponse>> getFollowers(@PathVariable Long userid){
        List<FollowResponse> followers =followService.getFollowers(userid);
        return ResponseEntity.ok(followers);
    }
@GetMapping("/{userid}/following")
    public ResponseEntity<List<FollowResponse>> getFollowing(@PathVariable Long userid){
        List<FollowResponse> following =  followService.getFollowing(userid);
        return  ResponseEntity.ok(following);
    }


}
