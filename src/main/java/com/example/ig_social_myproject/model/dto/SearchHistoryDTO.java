package com.example.ig_social_myproject.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchHistoryDTO {
    private Integer searchID;
    private Integer userID;
    private String query;
    private LocalDateTime searchedAt;
}