package com.example.ig_social_myproject.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeDTO {
    private Integer likeID;
    private Integer postID;
    private Integer userID;
    private LocalDateTime createdAt;
}