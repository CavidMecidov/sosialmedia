package com.sosialmedia.sosialmedia.mapper;

import com.sosialmedia.sosialmedia.dto.PostResponse;
import com.sosialmedia.sosialmedia.dto.UserSummaryResponse;
import com.sosialmedia.sosialmedia.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    public static PostResponse toResponse(Post post){
        return PostResponse.builder()
                .id(post.getId())
                .content(post.getContent())
                .viewCount(post.getViewCount())
                .createdDate(post.getCreatedDate())
                .modifiedDate(post.getModifiedDate())
                .user(
                        UserSummaryResponse.builder()
                                .id(post.getUser().getId())
                                .name(post.getUser().getName())
                                .surname(post.getUser().getSurname())
                                .username(post.getUser().getUsername())
                                .build()
                )
                .build();
    }
}
