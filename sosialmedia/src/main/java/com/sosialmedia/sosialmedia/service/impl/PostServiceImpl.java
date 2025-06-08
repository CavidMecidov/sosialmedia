package com.sosialmedia.sosialmedia.service.impl;

import com.sosialmedia.sosialmedia.dto.PostRequestDto;
import com.sosialmedia.sosialmedia.dto.PostResponse;
import com.sosialmedia.sosialmedia.entity.File;
import com.sosialmedia.sosialmedia.entity.Post;
import com.sosialmedia.sosialmedia.entity.User;
import com.sosialmedia.sosialmedia.exception.ConflictException;
import com.sosialmedia.sosialmedia.exception.NotFoundException;
import com.sosialmedia.sosialmedia.mapper.PostMapper;
import com.sosialmedia.sosialmedia.repository.FileRepository;
import com.sosialmedia.sosialmedia.repository.FollowRepository;
import com.sosialmedia.sosialmedia.repository.PostRepository;
import com.sosialmedia.sosialmedia.repository.UserRepository;
import com.sosialmedia.sosialmedia.service.PostService;
import com.sosialmedia.sosialmedia.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.AccessDeniedException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final PostMapper postMapper;
    private final FileRepository fileRepository;

    @Override
    public Post create(PostRequestDto postRequestDto, MultipartFile file, String cleanedFilename) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
        Post post = new Post();
        post.setUser(user);
        post.setTitle(postRequestDto.getTitle());
        post.setContent(postRequestDto.getContent());
        Post savedPost = postRepository.save(post);
        if (file != null && !file.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + cleanedFilename;
            FileUtil.saveFile(fileName, file);

            File fileEntity = new File();
            fileEntity.setName(fileName);
            fileEntity.setType(file.getContentType());
            fileEntity.setPost(savedPost);
            fileEntity.setUser(user);
            fileRepository.save(fileEntity);
        }
        return savedPost;

    }

    @Override
    public Post update(Long id, Post update) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();//username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Post existPost = postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Post not found"));

        if (!existPost.getUser().getUserid().equals(user.getUserid())) {
            throw new ConflictException("You can only update your own post");
        }

        existPost.setTitle(update.getTitle());
        existPost.setContent(update.getContent());
        return postRepository.save(existPost);
    }


    @Override
    public void delete(Long id, Long userid) throws AccessDeniedException {
        User user = userRepository.findById(userid)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Post existPost = postRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Post not found"));

        if (!existPost.getUser().getUserid().equals(userid)) {
            throw new AccessDeniedException("You can delete only own posts");
        }
        postRepository.delete(existPost);

    }

    @Override
    public List<PostResponse> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(PostMapper::toResponse)
                .toList();

    }

    @Override
    public List<Post> getByUserId(Long userid) {
        return postRepository.findByUserId(userid);
    }

    @Override
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public List<PostResponse> getAllDiscoverPosts() {
          List<Post>posts =postRepository.findAllByOrderByCreatedDateDesc();
          return posts.stream()
                  .map(PostMapper::toResponse)
                  .toList();
    }

    @Override
    public List<Post> getSharePosts(Long userid) {
        List<Long> followUserId = followRepository.findFollowedUserIds(userid);
        if(followUserId.isEmpty()){
            return Collections.emptyList();
        }
        return  postRepository.findByUserIdInOrderByCreatedDateDesc(followUserId);
    }
}
