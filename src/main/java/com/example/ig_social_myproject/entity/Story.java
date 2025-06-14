package com.example.ig_social_myproject.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import com.example.ig_social_myproject.utils._enum.MediaType;

@Data
@Entity
@Table(name = "stories")
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storyid")
    private Integer storyID;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    @Column(name = "mediaurl", nullable = false, length = 255)
    private String mediaURL;

    @Column(name = "mediatype", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private MediaType mediaType;

    @Column(name = "createdat", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "expiresat", columnDefinition = "TIMESTAMP GENERATED ALWAYS AS (createdat + INTERVAL '24 hours') STORED") // Lưu
    private LocalDateTime expiresAt;
}