package com.aluraforohub.forohub.persistence.user;

import com.aluraforohub.forohub.persistence.Authentication.RoleEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Set;

public record DatosRegistroRole(
        Long id,
        @Enumerated(EnumType.STRING)
        RoleEnum roleEnum,
        Set<DatosRegistroPermission> permissions
) {

    public DatosRegistroRole(Long id, RoleEnum roleEnum) {
        this(id, roleEnum, Set.of());
    }
}