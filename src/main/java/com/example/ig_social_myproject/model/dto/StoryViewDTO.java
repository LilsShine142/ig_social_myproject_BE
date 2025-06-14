package com.example.ig_social_myproject.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoryViewDTO {
    private Integer viewID;
    private Integer storyID;
    private Integer userID;
    private LocalDateTime viewedAt;
}