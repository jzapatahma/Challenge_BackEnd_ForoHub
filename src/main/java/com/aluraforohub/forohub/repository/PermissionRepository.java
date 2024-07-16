package com.aluraforohub.forohub.repository;

import com.aluraforohub.forohub.persistence.Authentication.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {
}

