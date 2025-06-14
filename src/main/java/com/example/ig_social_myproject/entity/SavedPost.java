package com.example.ig_social_myproject.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import com.example.ig_social_myproject.entity.keys.KeySavePostId;

@Data
@Entity
@Table(name = "savedposts")
@NoArgsConstructor
public class SavedPost {
    @EmbeddedId
    private KeySavePostId id;

    @MapsId("userid")
    @ManyToOne
    @JoinColumn(name = "userid", nullable = false, insertable = false, updatable = false)
    private User user;

    @MapsId("postid")
    @ManyToOne
    @JoinColumn(name = "postid", nullable = false, insertable = false, updatable = false)
    private Post post;

    @Column(name = "savedat", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime savedAt;

}