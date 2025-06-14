package com.example.ig_social_myproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "savedPosts")
public class SavedPost {
    @Id
    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "PostID", nullable = false)
    private Post post;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime savedAt;
}