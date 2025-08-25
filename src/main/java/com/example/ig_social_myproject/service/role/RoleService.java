package com.example.ig_social_myproject.service.role;

import com.example.ig_social_myproject.entity.Role;
import com.example.ig_social_myproject.repository.RoleRepository;
import com.example.ig_social_myproject.exception.ResourceNotFoundException;
import com.example.ig_social_myproject.exception.DuplicateResourceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<Role> findById(Integer roleId) {
        return roleRepository.findById(roleId);
    }

    public Optional<Role> findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    public String getRoleNameByRoleId(Integer roleId) {
        return roleRepository.findById(roleId)
                .map(Role::getRoleName)
                .orElse("USER");
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAllByOrderByRoleName();
    }

    public Role createRole(Role role) {
        if (roleRepository.existsByRoleName(role.getRoleName())) {
            throw new DuplicateResourceException("Role với tên '" + role.getRoleName() + "' đã tồn tại");
        }

        if (role.getCreatedAt() == null) {
            role.setCreatedAt(LocalDateTime.now());
        }
        if (role.getIsDefault() == null) {
            role.setIsDefault(false);
        }

        return roleRepository.save(role);
    }

    public Role updateRole(Integer roleId, Role role) {
        Role existingRole = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy role với ID: " + roleId));

        if (role.getRoleName() != null && !role.getRoleName().equals(existingRole.getRoleName())) {
            if (roleRepository.existsByRoleName(role.getRoleName())) {
                throw new DuplicateResourceException("Role với tên '" + role.getRoleName() + "' đã tồn tại");
            }
            existingRole.setRoleName(role.getRoleName());
        }

        if (role.getRoleDescription() != null) {
            existingRole.setRoleDescription(role.getRoleDescription());
        }

        if (role.getIsDefault() != null) {
            existingRole.setIsDefault(role.getIsDefault());
        }

        return roleRepository.save(existingRole);
    }

    // public void deleteRole(Integer roleId) {
    //     if (!roleRepository.existsById(roleId)) {
    //         throw new ResourceNotFoundException("Không tìm thấy role với ID: " + roleId);
    //     }

    //     Long userCount = roleRepository.countUsersWithRole(roleId);
    //     if (userCount > 0) {
    //         throw new IllegalStateException("Không thể xóa role vì vẫn còn " + userCount + " user đang sử dụng");
    //     }

    //     roleRepository.deleteById(roleId);
    // }

    public boolean existsById(Integer roleId) {
        return roleRepository.existsById(roleId);
    }

    public Role getDefaultRole() {
        return roleRepository.findByIsDefaultTrue()
                .orElseGet(() -> {
                    // Tạo default role nếu chưa có
                    Role defaultRole = Role.builder()
                            .roleName("USER")
                            .roleDescription("Default user role")
                            .isDefault(true)
                            .createdAt(LocalDateTime.now())
                            .build();
                    return roleRepository.save(defaultRole);
                });
    }

    // Helper methods
    public void initializeDefaultRoles() {
        try {
            if (!roleRepository.existsByRoleName("USER")) {
                createRole(Role.builder()
                        .roleName("USER")
                        .roleDescription("Standard user role")
                        .isDefault(true)
                        .build());
                System.out.println("✅ Created default USER role");
            }

            if (!roleRepository.existsByRoleName("ADMIN")) {
                createRole(Role.builder()
                        .roleName("ADMIN")
                        .roleDescription("Administrator role")
                        .isDefault(false)
                        .build());
                System.out.println("✅ Created ADMIN role");
            }
        } catch (Exception e) {
            System.err.println("❌ Error initializing default roles: " + e.getMessage());
        }
    }
}