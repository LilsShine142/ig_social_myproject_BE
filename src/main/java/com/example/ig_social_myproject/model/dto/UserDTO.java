package com.example.ig_social_myproject.model.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Integer userID;
    private String username;
    private String email;
    private String passwordHash;
    private String fullName;
    private String phoneNumber;
    private String profilePicture;
    private String bio;
    private Boolean isPrivate;
    private Boolean isVerified;
    private LocalDateTime createdAt;
    // Các trường mới
    private Integer roleId;
    // private String roleName; // Tên role thay vì chỉ ID
    private Boolean isActive;
    private Boolean isLocked;
    private LocalDateTime lastLogin;

    // Giữ lại nếu vẫn muốn hỗ trợ multiple roles
    private Set<String> roles; // Danh sách tên roles
    private Set<String> permissions; // Danh sách permissions
}