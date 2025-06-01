package com.sosialmedia.sosialmedia.controller;

import com.sosialmedia.sosialmedia.dto.BaseResponse;
import com.sosialmedia.sosialmedia.dto.FollowResponse;
import com.sosialmedia.sosialmedia.entity.User;
import com.sosialmedia.sosialmedia.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user_follows")
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;

    @PostMapping("/follow/{followingId}")
    public BaseResponse<String> followUser(@AuthenticationPrincipal User user,
                                           @PathVariable Long followingId) {
        followService.followUser(user.getId(), followingId);
        BaseResponse<String> baseResponse = new BaseResponse<>();
        baseResponse.setData("Followed user with id: " + followingId);
        baseResponse.setMessage("Follow successful");
        baseResponse.setSuccess(true);
        return baseResponse;

    }

    @DeleteMapping("/unfollow/{followingId}")
    public BaseResponse<String> unFollow(@AuthenticationPrincipal User user,
                                           @PathVariable Long followingId) {
        followService.unFollowUser(user.getId(), followingId);
        BaseResponse<String> baseResponse = new BaseResponse<>();
        baseResponse.setData("Following user with id: " + followingId);
        baseResponse.setMessage("Following successful");
        baseResponse.setSuccess(true);
        return baseResponse;
    }

    @GetMapping("/{userid}/follower")
    public BaseResponse<List<FollowResponse>> getFollowers(@PathVariable Long userid) {
        List<FollowResponse> followers = followService.getFollowers(userid);
        BaseResponse<List<FollowResponse>> baseResponse = new BaseResponse<>();
        baseResponse.setData(followers);
        baseResponse.setMessage("Followers brought successfully");
        baseResponse.setSuccess(true);
        return baseResponse;
    }

    @GetMapping("/{userid}/following")
    public BaseResponse<List<FollowResponse>> getFollowing(@PathVariable Long userid) {
        List<FollowResponse> following = followService.getFollowing(userid);
        BaseResponse<List<FollowResponse>> baseResponse = new BaseResponse<>();
        baseResponse.setData(following);
        baseResponse.setMessage("Following list brought successfully");
        baseResponse.setSuccess(true);
        return baseResponse;
    }


}
