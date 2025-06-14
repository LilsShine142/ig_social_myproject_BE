package com.example.ig_social_myproject.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PollOptionDTO {
    private Integer optionID;
    private Integer pollID;
    private String text;
    private Integer voteCount;
}