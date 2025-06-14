package com.example.ig_social_myproject.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PollDTO {
    private Integer pollID;
    private Integer postID;
    private Integer storyID;
    private String question;
    private LocalDateTime expiresAt;
}