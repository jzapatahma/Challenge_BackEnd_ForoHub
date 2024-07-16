package com.aluraforohub.forohub.repository;

import com.aluraforohub.forohub.persistence.Authentication.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByRoleEnum(String nombre);
}
