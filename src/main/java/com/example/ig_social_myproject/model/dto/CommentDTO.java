package com.example.ig_social_myproject.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDTO {
    private Integer commentID;
    private Integer postID;
    private Integer userID;
    private String content;
    private Integer parentCommentID;
    private LocalDateTime createdAt;
}