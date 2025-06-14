package com.example.ig_social_myproject.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "polls")
@NoArgsConstructor
@AllArgsConstructor
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pollid")
    private Integer pollID;

    @ManyToOne
    @JoinColumn(name = "postid")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "storyid")
    private Story story;

    @Column(name = "question", nullable = false, columnDefinition = "TEXT")
    private String question;

    @Column(name = "expiresat")
    private LocalDateTime expiresAt;

}