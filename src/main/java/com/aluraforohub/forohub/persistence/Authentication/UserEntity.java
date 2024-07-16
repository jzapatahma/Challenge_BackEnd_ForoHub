package com.aluraforohub.forohub.persistence.Authentication;

import com.aluraforohub.forohub.persistence.user.DatosActualizarUser;
import com.aluraforohub.forohub.persistence.user.DatosRegistroUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@Column(unique = true)
    private String username;
    private String password;
    private boolean isEnable;
    private boolean accountNoExpired;
    private boolean accountNoLocked;
    private boolean credentialNoExpired;
    //
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="users_roles",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<RoleEntity> roles = new HashSet<>();

    public UserEntity(DatosRegistroUser datosRegistroUser) {
        //this.id = datosRegistroUser.id();
        this.username = datosRegistroUser.username();
        this.password = datosRegistroUser.password();
        this.isEnable = datosRegistroUser.isEnable();
        this.accountNoExpired = datosRegistroUser.accountNoExpired();
        this.accountNoLocked = datosRegistroUser.accountNoLocked();
        this.credentialNoExpired = datosRegistroUser.credentialNoExpired();
        // Convertir roles y permisos
        this.roles = datosRegistroUser.roles().stream()
                .map(role -> {
                    Set<PermissionEntity> permissions = role.permissions().stream()
                            .map(permission -> new PermissionEntity(permission.name())) // Mapea a PermissionEntity
                            .collect(Collectors.toSet());
                    return new RoleEntity(role.roleEnum(), permissions); // Crea RoleEntity
                })
                .collect(Collectors.toSet());
    }


    public void actualizarDatos(DatosActualizarUser datosActualizarUser) {
        if (datosActualizarUser.username() != null) {
            this.username = datosActualizarUser.username();
        }
        if (datosActualizarUser.password() != null) {
            this.password = datosActualizarUser.password();
        }
        // se pueden incluir otros mas a actualizar
//        this.isEnable = datosActualizarUser.isEnable();
//        this.accountNoExpired = datosActualizarUser.accountNoExpired();
//        this.accountNoLocked = datosActualizarUser.accountNoLocked();
//        this.credentialNoExpired = datosActualizarUser.credentialNoExpired();
    }

    // esta configuracion es la mas sencilla y funciona bien
    //    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //    private Set<RoleEntity> roles = new HashSet<>();

}
