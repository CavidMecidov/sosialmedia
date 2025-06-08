package com.sosialmedia.sosialmedia.mapper;

import com.sosialmedia.sosialmedia.dto.UserResponse;
import com.sosialmedia.sosialmedia.entity.User;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {
    public UserResponse toResponse(User user){
        return UserResponse.builder()
                .userid(user.getUserid())
                .name(user.getName())
                .surname(user.getSurname())
                .bio(user.getBio())
                .gender(user.getGender())
                .phoneNumber(user.getPhoneNumber())
                .username(user.getUsername())
                .email(user.getEmail())
                .birthdate(user.getBirthdate())
                .createdDate(user.getCreatedDate())
                .modifiedDate(user.getModifiedDate())
                .build();
    }

    }

