package com.example.ig_social_myproject.entity;

import com.example.ig_social_myproject.entity.keys.KeyPostHashtagId;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "posthashtags")
public class PostHashtag {

    @EmbeddedId
    private KeyPostHashtagId id;

    @MapsId("postid")
    @ManyToOne
    @JoinColumn(name = "postid", insertable = false, updatable = false)
    private Post post;

    @MapsId("hashtagid")
    @ManyToOne
    @JoinColumn(name = "hashtagid", insertable = false, updatable = false)
    private Hashtag hashtag;
}
