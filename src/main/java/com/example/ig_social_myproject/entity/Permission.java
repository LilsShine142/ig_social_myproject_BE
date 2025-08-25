package com.example.ig_social_myproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "permissions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private Integer permissionId;

    @Column(name = "permission_code", unique = true, nullable = false, length = 100)
    private String permissionCode;

    @Column(name = "permission_name", nullable = false, length = 100)
    private String permissionName;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "module", length = 50)
    private String module;

    @OneToMany(mappedBy = "permission", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<RolePermission> rolePermissions;
}