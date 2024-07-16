package com.aluraforohub.forohub.persistence.user;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarUser(

        @NotNull Long id,
        String username,
        String password,
        boolean isEnable,
        boolean accountNoExpired,
        boolean accountNoLocked,
        boolean credentialNoExpired

) {



}
