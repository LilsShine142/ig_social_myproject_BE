package com.example.ig_social_myproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "storyViews")
public class StoryView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer viewID;

    @ManyToOne
    @JoinColumn(name = "StoryID", nullable = false)
    private Story story;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime viewedAt;

    @Table(uniqueConstraints = @UniqueConstraint(columnNames = { "StoryID", "UserID" }))
    private static class UniqueConstraintHolder {
    }
}