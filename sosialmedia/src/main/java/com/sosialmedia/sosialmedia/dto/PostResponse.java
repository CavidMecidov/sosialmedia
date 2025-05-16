package com.sosialmedia.sosialmedia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private Long id;
    private String content;
    private String imageUrl;
    private String videoUrl;
    private Integer viewCount;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private UserSummaryResponse user;
}
