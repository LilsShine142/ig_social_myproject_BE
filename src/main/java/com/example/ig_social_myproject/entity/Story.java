package com.example.ig_social_myproject.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "stories")
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer storyID;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @Column(nullable = false, length = 255)
    private String mediaURL;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private MediaType mediaType;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP GENERATED ALWAYS AS (CreatedAt + INTERVAL '24 hours') STORED")
    private LocalDateTime expiresAt;

    public enum MediaType {
        image, video
    }
}