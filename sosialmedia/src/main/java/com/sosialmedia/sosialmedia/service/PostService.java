package com.sosialmedia.sosialmedia.service;

import com.sosialmedia.sosialmedia.dto.PostRequestDto;
import com.sosialmedia.sosialmedia.dto.PostResponse;
import com.sosialmedia.sosialmedia.entity.Post;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

public interface PostService {
    Post create(PostRequestDto postRequestDto, MultipartFile file, String cleanedFilename);

    Post update(Long id, Post update);

    void delete(Long id, Long userid) throws AccessDeniedException;

    List<PostResponse> getAllPosts();

    List<Post> getByUserId(Long userid);

    Optional<Post> getPostById(Long id);

    List<PostResponse> getAllDiscoverPosts();

    List<Post> getSharePosts(Long userid);

}
