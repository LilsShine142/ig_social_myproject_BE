package com.example.ig_social_myproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "hashtags")
@NoArgsConstructor
@AllArgsConstructor
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hashtagid")
    private Integer hashtagID;

    @Column(name = "name", unique = true, nullable = false, length = 255)
    private String name;

}