package com.example.ig_social_myproject.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoryDTO {
    private Integer storyID;
    private Integer userID;
    private String mediaURL;
    private String mediaType;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
}