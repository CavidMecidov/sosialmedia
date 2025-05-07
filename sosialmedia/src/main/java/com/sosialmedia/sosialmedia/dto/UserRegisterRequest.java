package com.sosialmedia.sosialmedia.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sosialmedia.sosialmedia.enums.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegisterRequest {
    @NotBlank(message = "Name can not be blank")
    private String name;
    @NotBlank(message = "Surname can not be blank")
    private String surname;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String bio;
    @NotBlank(message = "Email can not be blank")
    @Email(message = "Invalid email format")
    private String email;
    @Size(min = 10, max = 10, message = "Phone_number must contain 10 characters")
    @Pattern(regexp = "\\d{10}", message = "Phone_number must contain only digits")
    private String phoneNumber;
    @NotBlank(message = "Username can not be blank")
    private String username;
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @NotBlank(message = "Password can not be blank")
    private String password;
    private String profilePictureUrl;


}
