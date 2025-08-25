package com.example.ig_social_myproject.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolePermissionDTO {
    private Integer rolePermissionId;
    private Integer roleId;
    private Integer permissionId;
    private LocalDateTime grantedAt;
    private Integer grantedByUserId;
}