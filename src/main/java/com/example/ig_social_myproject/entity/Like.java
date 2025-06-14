package com.example.ig_social_myproject.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import com.example.ig_social_myproject.entity.keys.KeyLiked;

@Entity
@Table(name = "likes")
@Data
@NoArgsConstructor
public class Like {
    @EmbeddedId
    private KeyLiked likeID;

    @MapsId("postid")
    @ManyToOne
    @JoinColumn(name = "postid", insertable = false, updatable = false)
    private Post post;

    @MapsId("userid")
    @ManyToOne
    @JoinColumn(name = "userid", insertable = false, updatable = false)
    private User user;

    @Column(name = "createdat", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

}