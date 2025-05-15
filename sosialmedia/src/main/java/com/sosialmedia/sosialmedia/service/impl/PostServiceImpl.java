package com.sosialmedia.sosialmedia.service.impl;

import com.sosialmedia.sosialmedia.entity.Post;
import com.sosialmedia.sosialmedia.entity.User;
import com.sosialmedia.sosialmedia.repository.PostRepository;
import com.sosialmedia.sosialmedia.repository.UserRepository;
import com.sosialmedia.sosialmedia.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public Post create(Post post) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        post.setUser(user);
        return postRepository.save(post);

    }

    @Override
    public Post update(Long id, Post update) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

        Post existPost = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));

        if (!existPost.getUser().getUserid().equals(user.getUserid())) {
            throw new RuntimeException("You can only update your own post");
        }

        existPost.setTittle(update.getTittle());
        existPost.setContent(update.getContent());
        existPost.setImageUrl(update.getImageUrl());
        existPost.setVideoUrl(update.getVideoUrl());
        return postRepository.save(existPost);
    }


    @Override
    public void delete(Long id, Long userid) {
        User user = userRepository.findById(userid).orElseThrow(() -> new RuntimeException("User not found"));

        Post existPost = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));

        if (!existPost.getUser().getUserid().equals(userid)) {
            throw new RuntimeException("You can delete only own posts");
        }
        postRepository.delete(existPost);

    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> getByUserId(Long userid) {
        return postRepository.findByUserId(userid);
    }

    @Override
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }
}
