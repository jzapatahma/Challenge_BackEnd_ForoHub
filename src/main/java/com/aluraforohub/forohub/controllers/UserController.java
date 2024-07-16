package com.aluraforohub.forohub.controllers;

import com.aluraforohub.forohub.persistence.Authentication.PermissionEntity;
import com.aluraforohub.forohub.persistence.Authentication.RoleEntity;
import com.aluraforohub.forohub.persistence.Authentication.UserEntity;
import com.aluraforohub.forohub.persistence.dto.PermissionDto;
import com.aluraforohub.forohub.persistence.dto.RoleDto;
import com.aluraforohub.forohub.persistence.dto.UserRequestDto;
import com.aluraforohub.forohub.persistence.user.DatosRegistroUser;
import com.aluraforohub.forohub.persistence.user.DatosRespuestaUser;
import com.aluraforohub.forohub.repository.PermissionRepository;
import com.aluraforohub.forohub.repository.RoleRepository;
import com.aluraforohub.forohub.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
//@PreAuthorize("denyAll")  // deniega el acceso a todos, pero lo configuramos en los mapping
@PreAuthorize("permitAll")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PermissionRepository permissionRepository;

    // Guardar un registro nuevo
    @PostMapping
    @PreAuthorize("hasAuthority('CREATE') or hasAuthority('REFACTOR')")
    @Transactional

    public ResponseEntity<String> createUser(@RequestBody UserRequestDto userRequest) {
        try {
            BCryptPasswordEncoder bPdEncoder = new BCryptPasswordEncoder();
            // Crear un nuevo usuario
            UserEntity newUser = new UserEntity();
            newUser.setUsername(userRequest.getUsername());
            newUser.setPassword(bPdEncoder.encode(userRequest.getPassword()));

            // Asociar roles y permisos existentes
            for (RoleDto roleDto : userRequest.getRoles()) {
                System.out.println("Nro Role: "+ roleDto.getId());
                RoleEntity role = roleRepository.findById(roleDto.getId())
                        .orElseThrow(() -> new IllegalArgumentException("Role not found: " + roleDto.getId()));

                // Verificar si el usuario ya tiene este rol
                if (!newUser.getRoles().contains(role)) {
                    newUser.getRoles().add(role);
                }
                // Asociar permisos al rol
                for (PermissionDto permissionDto : roleDto.getPermissions()) {
                    System.out.println("Nro Permiso: "+ permissionDto.getId());
                    PermissionEntity permission = permissionRepository.findById(permissionDto.getId())
                            .orElseThrow(() -> new IllegalArgumentException("Permission not found: " + permissionDto.getId()));

                    // Verificar si el rol ya tiene este permiso
                    if (!role.getPermissions().contains(permission)) {
                        role.getPermissions().add(permission);
                    }
                }
                newUser.getRoles().add(role);
            }
            // Guardar el nuevo usuario
            userRepository.save(newUser);
            return ResponseEntity.ok("User created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating user: " + e.getMessage());
        }
    }


    @GetMapping("/get")
    //@PreAuthorize("hasAuthority('READ')")
    public String helloGet(){
        return "Hello World - GET";
    }

    @PutMapping("/put")
    public String helloPut(){
        return "Hello World - PUT";
    }

    @DeleteMapping("/delete")
    public String helloDelete(){
        return "Hello World - DELETE";
    }

    @PatchMapping("/patch")
    //@PreAuthorize("hasAuthority('REFACTOR')")
    public String helloPatch(){
        return "Hello World - PATCH";
    }


}
