package com.sosialmedia.sosialmedia.controller;

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
    public ResponseEntity<Post> created(@RequestBody @Valid Post post) {
        Post created = postService.create(post);
        return ResponseEntity.ok(created);
    }

    @PutMapping
    public ResponseEntity<Post> update(@PathVariable Long id, @RequestBody Post update) {
        Post updatePost = postService.update(id, update);
        return ResponseEntity.ok(updatePost);
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        List<PostResponse> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@RequestParam Long id) {
        return postService.getPostById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-userid")
    public ResponseEntity<List<Post>> getByUserId(@PathVariable Long userid) {
        return ResponseEntity.ok(postService.getByUserId(userid));
    }
    @GetMapping("/discover")
    public ResponseEntity<List<PostResponse>>getDiscoverPosts(){
        return ResponseEntity.ok(postService.getAllDiscoverPosts());
    }
    @GetMapping("/Shares")
    public ResponseEntity<List<Post>>getShare(@RequestParam Long userid){
        List<Post> sharePost =postService.getSharePosts(userid);
        return ResponseEntity.ok(sharePost);
    }
}
