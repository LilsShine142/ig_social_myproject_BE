package com.example.ig_social_myproject.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import com.example.ig_social_myproject.entity.keys.KeyFollow;

@Entity
@Table(name = "follows")
@Data
@NoArgsConstructor
public class Follow {
    @EmbeddedId
    private KeyFollow followID;

    @MapsId("followerid")
    @ManyToOne
    @JoinColumn(name = "followerid", insertable = false, updatable = false)
    private User follower;

    @MapsId("followeeid")
    @ManyToOne
    @JoinColumn(name = "followeeid", insertable = false, updatable = false)
    private User followee;

    @Column(name = "createdat", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

}