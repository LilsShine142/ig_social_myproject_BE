package com.example.ig_social_myproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "postHashtags")
public class PostHashtag {
    @Id
    @ManyToOne
    @JoinColumn(name = "PostID", nullable = false)
    private Post post;

    @Id
    @ManyToOne
    @JoinColumn(name = "HashtagID", nullable = false)
    private Hashtag hashtag;
}
