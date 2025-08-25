package com.example.ig_social_myproject.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "users") // PostgreSQL thường dùng chữ thường cho tên bảng
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid") // PostgreSQL sẽ chuyển thành chữ thường
    private Integer userID;

    @Column(name = "username", unique = true, nullable = false, length = 255)
    private String username;

    @Column(name = "email", unique = true, nullable = false, length = 255)
    private String email;

    @Column(name = "passwordhash", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "fullname", length = 255)
    private String fullName;

    @Column(name = "phonenumber", length = 20)
    private String phoneNumber;

    @Column(name = "profilepicture", length = 255)
    private String profilePicture;

    @Column(name = "bio", columnDefinition = "TEXT")
    private String bio;

    @Column(name = "isprivate", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isPrivate = false;

    @Column(name = "isverified", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isVerified = false;

    @Column(name = "createdat", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_locked")
    private Boolean isLocked;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    // Relationship với Role (nếu dùng role_id trực tiếp)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;

    // Relationships với roles thông qua user_roles (nếu vẫn muốn giữ many-to-many)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserRole> userRoles;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (isPrivate == null) {
            isPrivate = false;
        }
        if (isVerified == null) {
            isVerified = false;
        }
        if (isActive == null) {
            isActive = true;
        }
        if (isLocked == null) {
            isLocked = false;
        }
    }
}