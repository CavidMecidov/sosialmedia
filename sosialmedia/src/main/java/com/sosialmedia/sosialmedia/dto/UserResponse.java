package com.sosialmedia.sosialmedia.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sosialmedia.sosialmedia.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private Long userid;
    private String username;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private String email;
    private Gender gender;
    private String bio;
    private String phoneNumber;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime modifiedDate;
}