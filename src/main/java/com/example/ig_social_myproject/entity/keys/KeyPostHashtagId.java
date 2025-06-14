package com.example.ig_social_myproject.entity.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyPostHashtagId implements Serializable {
    @Column(name = "postid")
    private Long postid;

    @Column(name = "hashtagid")
    private Long hashtagid;
}