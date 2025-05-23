package com.sosialmedia.sosialmedia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserLoginRequest {
    @NotBlank(message = "Username can not be blank")
    private String username;
    @NotBlank(message = "Password can not be blank")
    @Size(min = 8)
    private String password;
}
