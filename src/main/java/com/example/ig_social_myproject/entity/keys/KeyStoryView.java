package com.example.ig_social_myproject.entity.keys;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyStoryView implements Serializable {
    @Column(name = "storyid")
    private Integer storyid;

    @Column(name = "userid")
    private Integer userid;

}
