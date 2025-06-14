package com.example.ig_social_myproject.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "messages")
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messageid")
    private Integer messageID;

    @ManyToOne
    @JoinColumn(name = "senderid", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiverid", nullable = false)
    private User receiver;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "mediaurl", length = 255)
    private String mediaURL;

    @Column(name = "isread", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isRead = false;

    @Column(name = "createdat", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updatedat", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}