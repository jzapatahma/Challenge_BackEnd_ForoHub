package com.aluraforohub.forohub.persistence.Authentication;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@Column(name = "roleName")
    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="roles_permissions",
            joinColumns = @JoinColumn(name="role_id"),
            inverseJoinColumns = @JoinColumn(name="permission_id"))
    private Set<PermissionEntity> permissions = new HashSet<>();

    public RoleEntity(RoleEnum roleEnum, Set<PermissionEntity> permissions) {
        this.roleEnum = roleEnum;
        this.permissions = permissions;
    }

    // Existe otra manera sencilla y es la siguiente
    //    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //    private Set<PermissionEntity> permissions = new HashSet<>();
}
