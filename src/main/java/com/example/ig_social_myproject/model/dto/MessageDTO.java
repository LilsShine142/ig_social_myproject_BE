package com.example.ig_social_myproject.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDTO {
    private Integer messageID;
    private Integer senderID;
    private Integer receiverID;
    private String content;
    private String mediaURL;
    private Boolean isRead;
    private LocalDateTime createdAt;
}