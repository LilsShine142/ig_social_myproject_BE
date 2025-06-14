package com.example.ig_social_myproject.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import com.example.ig_social_myproject.utils._enum.NotificationType;

@Data
@Entity
@Table(name = "notifications") 
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notificationid") 
    private Integer notificationID;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false) 
    private User user;

    @ManyToOne
    @JoinColumn(name = "actorid", nullable = false) 
    private User actor;

    @Column(name = "type", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @ManyToOne
    @JoinColumn(name = "postid") 
    private Post post;

    @ManyToOne
    @JoinColumn(name = "commentid")
    private Comment comment;

    @Column(name = "isread", columnDefinition = "BOOLEAN DEFAULT FALSE") 
    private Boolean isRead = false;

    @Column(name = "createdat", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP") 
    private LocalDateTime createdAt;
}