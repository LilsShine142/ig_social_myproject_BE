package com.example.ig_social_myproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "pollOptions")
public class PollOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer optionID;

    @ManyToOne
    @JoinColumn(name = "PollID", nullable = false)
    private Poll poll;

    @Column(nullable = false, length = 255)
    private String text;

    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer voteCount = 0;
}