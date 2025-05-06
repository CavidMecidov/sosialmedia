package com.sosialmedia.sosialmedia.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
@Data
public class UserResponse {
    private Long userid;
    private String username;
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDate createdDate;
    @JsonFormat(pattern = "yyy-MM-dd HH:mm")
    private LocalDate modifiedDate;

}
