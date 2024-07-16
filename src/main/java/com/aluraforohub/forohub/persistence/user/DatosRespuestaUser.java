package com.aluraforohub.forohub.persistence.user;

public record DatosRespuestaUser(
        Long id,
        String username,
        String password,
        boolean isEnable,
        boolean accountNoExpired,
        boolean accountNoLocked,
        boolean credentialNoExpired
) {
}
