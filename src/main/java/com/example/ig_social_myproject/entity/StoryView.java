package com.example.ig_social_myproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import com.example.ig_social_myproject.entity.keys.KeyStoryView;

@Entity
@Table(name = "storyviews")
@Data
@NoArgsConstructor
public class StoryView {
    @EmbeddedId
    private KeyStoryView viewID;

    @MapsId("storyid") // Phải khớp với tên field trong StoryViewId
    @ManyToOne
    @JoinColumn(name = "storyid", nullable = false, insertable = false, updatable = false)
    private Story story;

    @MapsId("userid") // Phải khớp với tên field trong StoryViewId
    @ManyToOne
    @JoinColumn(name = "userid", nullable = false, insertable = false, updatable = false)
    private User user;

    @Column(name = "viewedat", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime viewedAt;

}