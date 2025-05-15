package com.sosialmedia.sosialmedia.mapper;

import com.sosialmedia.sosialmedia.dto.FollowResponse;
import com.sosialmedia.sosialmedia.entity.Follow;
import com.sosialmedia.sosialmedia.entity.User;
import org.springframework.stereotype.Component;

@Component
public class FollowMapper {

    public FollowResponse toFollowResponse(User user){
        return FollowResponse.builder()
                .id(user.getUserid())
                .name(user.getName())
                .surname(user.getSurname())
                .username(user.getUsername())
                .bio(user.getBio())
                .profilePictureUrl(user.getProfilePictureUrl())
                .createdDate(user.getCreatedDate())
                .modifiedDate(user.getModifiedDate())
                .build();
    }
}
