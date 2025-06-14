package com.example.ig_social_myproject.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavedPostDTO {
    private Integer userID;
    private Integer postID;
    private LocalDateTime savedAt;
}