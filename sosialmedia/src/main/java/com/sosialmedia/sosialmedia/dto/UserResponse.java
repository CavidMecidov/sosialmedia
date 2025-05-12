package com.sosialmedia.sosialmedia.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sosialmedia.sosialmedia.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long userid;
    private String username;
    private String name;
    private String surname;
    private String email;
    private Gender gender;
    private String bio;
    private String phoneNumber;
    private String profilePictureUrl;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDate createdDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDate modifiedDate;
}