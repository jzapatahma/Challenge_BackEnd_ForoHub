package com.aluraforohub.forohub.persistence.user;

import com.aluraforohub.forohub.persistence.Authentication.UserEntity;

public record DatosListadoUser(

        Long id,
        String username,
        String password,
        boolean isEnable,
        boolean accountNoExpired,
        boolean accountNoLocked,
        boolean credentialNoExpired

) {
    public DatosListadoUser(UserEntity users){
        this(users.getId(),
                users.getUsername(),
                users.getPassword(),
                users.isEnable(),
                users.isAccountNoExpired(),
                users.isAccountNoLocked(),
                users.isCredentialNoExpired());
    }


}
