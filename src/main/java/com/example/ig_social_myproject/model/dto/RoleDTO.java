package com.example.ig_social_myproject.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDTO {
    private Integer roleId;
    private String roleName;
    private String roleDescription;
    private LocalDateTime createdAt;
    private Boolean isDefault;

}