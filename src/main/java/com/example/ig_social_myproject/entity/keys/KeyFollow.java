package com.example.ig_social_myproject.entity.keys;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyFollow implements Serializable {
    @Column(name = "followerid")
    private Long followerid;

    @Column(name = "followeeid")
    private Long followeeid;
}
