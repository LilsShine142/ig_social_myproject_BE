package com.example.ig_social_myproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import com.example.ig_social_myproject.utils._enum.MediaType;

@Data
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postid")
    private Integer postID;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    @Column(name = "contenturl", nullable = false, length = 255)
    private String contentURL;

    @Column(name = "mediatype", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private MediaType mediaType;

    @Column(name = "caption", columnDefinition = "TEXT")
    private String caption;

    @Column(name = "location", length = 255)
    private String location;

    @Column(name = "likecount", columnDefinition = "INT DEFAULT 0")
    private Integer likeCount = 0;

    @Column(name = "commentcount", columnDefinition = "INT DEFAULT 0")
    private Integer commentCount = 0;

    @Column(name = "createdat", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

}