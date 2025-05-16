package com.sosialmedia.sosialmedia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserSummaryResponse {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String profilePictureUrl;
}
