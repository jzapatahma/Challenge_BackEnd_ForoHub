package com.aluraforohub.forohub.persistence.user;

import com.aluraforohub.forohub.persistence.Authentication.RoleEntity;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record DatosRegistroUser(
        @NotBlank String username,
        @NotBlank String password,
        boolean isEnable,
        boolean accountNoExpired,
        boolean accountNoLocked,
        boolean credentialNoExpired,
        Set<RoleEntity> roles
) {

}
