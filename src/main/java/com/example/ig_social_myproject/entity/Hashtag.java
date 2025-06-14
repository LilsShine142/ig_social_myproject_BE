package com.example.ig_social_myproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "hashtags")
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hashtagID;

    @Column(unique = true, nullable = false, length = 255)
    private String name;
}