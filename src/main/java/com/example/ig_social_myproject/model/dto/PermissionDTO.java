package com.example.ig_social_myproject.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionDTO {
    private Integer permissionId;
    private String permissionCode;
    private String permissionName;
    private String description;
    private String module;

}