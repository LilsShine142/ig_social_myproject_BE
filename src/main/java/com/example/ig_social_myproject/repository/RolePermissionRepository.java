package com.example.ig_social_myproject.repository;

import com.example.ig_social_myproject.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, Integer> {

    // Sử dụng đúng convention: Role_RoleId và Permission_PermissionId
    boolean existsByRole_RoleIdAndPermission_PermissionId(Integer roleId, Integer permissionId);

    Optional<RolePermission> findByRole_RoleIdAndPermission_PermissionId(Integer roleId, Integer permissionId);

    List<RolePermission> findByRole_RoleId(Integer roleId);

    List<RolePermission> findByPermission_PermissionId(Integer permissionId);


}