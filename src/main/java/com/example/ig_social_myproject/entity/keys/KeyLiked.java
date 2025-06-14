package com.example.ig_social_myproject.entity.keys;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyLiked implements Serializable {
    @Column(name = "postid")
    private Integer postid;

    @Column(name = "userid")
    private Integer userid;

}
