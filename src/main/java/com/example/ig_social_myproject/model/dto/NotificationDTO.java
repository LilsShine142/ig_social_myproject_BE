package com.example.ig_social_myproject.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDTO {
    private Integer notificationID;
    private Integer userID;
    private Integer actorID;
    private String type;
    private Integer postID;
    private Integer commentID;
    private Boolean isRead;
    private LocalDateTime createdAt;
}