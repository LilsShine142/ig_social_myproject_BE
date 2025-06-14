package com.example.ig_social_myproject.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDTO {
    private Integer postID;
    private Integer userID;
    private String contentURL;
    private String mediaType;
    private String caption;
    private String location;
    private Integer likeCount;
    private Integer commentCount;
    private LocalDateTime createdAt;
}