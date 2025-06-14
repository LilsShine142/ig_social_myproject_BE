package com.example.ig_social_myproject.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostHashtagDTO {
    private Integer postID;
    private Integer hashtagID;
}