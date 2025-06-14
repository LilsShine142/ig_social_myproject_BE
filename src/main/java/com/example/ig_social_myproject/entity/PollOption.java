package com.example.ig_social_myproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "polloptions")
public class PollOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "optionid")
    private Integer optionID;

    @ManyToOne
    @JoinColumn(name = "pollid", nullable = false)
    private Poll poll;

    @Column(name = "text", nullable = false, length = 255)
    private String text;

    @Column(name = "votecount", columnDefinition = "INT DEFAULT 0")
    private Integer voteCount = 0;

}