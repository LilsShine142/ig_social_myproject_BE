package com.example.ig_social_myproject.repository;

import com.example.ig_social_myproject.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

        // Basic methods
        Optional<Role> findByRoleName(String roleName);

        boolean existsByRoleName(String roleName);

        Optional<Role> findByIsDefaultTrue();

        List<Role> findAllByOrderByRoleName();

        List<Role> findByIsDefaultFalse();
}