package com.sosialmedia.sosialmedia.controller;

import com.sosialmedia.sosialmedia.dto.BaseResponse;
import com.sosialmedia.sosialmedia.dto.FollowResponse;
import com.sosialmedia.sosialmedia.dto.PostResponse;
import com.sosialmedia.sosialmedia.entity.Post;
import com.sosialmedia.sosialmedia.service.PostService;
import jakarta.validation.OverridesAttribute;
import jakarta.validation.Valid;
import jakarta.validation.groups.ConvertGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping
    public BaseResponse<Post> created(@RequestBody @Valid Post post) {
        Post created = postService.create(post);
        BaseResponse<Post> baseResponse = new BaseResponse<>();
        baseResponse.setData(created);
        baseResponse.setMessage("Post created successfully");
        baseResponse.setSuccess(true);
        return baseResponse;
    }

    @PutMapping
    public BaseResponse<Post> update(@PathVariable Long id, @RequestBody Post update) {
        Post updatePost = postService.update(id, update);
        BaseResponse<Post> baseResponse = new BaseResponse<>();
        baseResponse.setData(updatePost);
        baseResponse.setMessage("Post updated successfully");
        baseResponse.setSuccess(true);
        return baseResponse;

    }

    @GetMapping
    public BaseResponse<List<PostResponse>> getAllPosts() {
        List<PostResponse> posts = postService.getAllPosts();
        BaseResponse<List<PostResponse>> baseResponse = new BaseResponse<>();
        baseResponse.setData(posts);
        baseResponse.setMessage("All posts called successfully");
        baseResponse.setSuccess(true);
        return baseResponse;


    }

    @GetMapping("/{id}")
    public BaseResponse<Post> getPostById(@RequestParam Long id) {
        return postService.getPostById(id)
                .map(post -> new BaseResponse<>(post, "Post found", true))
                .orElseGet(() -> new BaseResponse<>(null, "Post not found", false));
    }

    @GetMapping("/by-userid")
    public BaseResponse<List<Post>> getByUserId(@PathVariable Long userid) {
        List<Post> posts = postService.getByUserId(userid);
        return new BaseResponse<>(posts, "Posts by user fetched successfully", true);
    }
    @GetMapping("/discover")
    public BaseResponse<List<PostResponse>>getDiscoverPosts(){
        List<PostResponse> posts = postService.getAllDiscoverPosts();
        return new BaseResponse<>(posts, "Discover posts fetched successfully", true);
    }
    @GetMapping("/Shares")
    public BaseResponse<List<Post>>getShare(@RequestParam Long userid){
        List<Post> sharePost = postService.getSharePosts(userid);
        return new BaseResponse<>(sharePost, "Shared posts fetched successfully", true);
    }
}
