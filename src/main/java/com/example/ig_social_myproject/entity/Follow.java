package com.example.ig_social_myproject.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Follows")
public class Follow {
    @Id
    @ManyToOne
    @JoinColumn(name = "FollowerID", nullable = false)
    private User follower;

    @Id
    @ManyToOne
    @JoinColumn(name = "FolloweeID", nullable = false)
    private User followee;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
}