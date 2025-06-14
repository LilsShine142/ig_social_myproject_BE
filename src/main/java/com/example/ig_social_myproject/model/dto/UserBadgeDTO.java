package com.example.ig_social_myproject.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserBadgeDTO {
    private Integer badgeID;
    private Integer userID;
    private String badgeType;
    private LocalDateTime awardedAt;
}