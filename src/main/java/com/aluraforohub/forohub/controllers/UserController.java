package com.aluraforohub.forohub.controllers;

import com.aluraforohub.forohub.persistence.Authentication.UserEntity;
import com.aluraforohub.forohub.persistence.user.DatosRegistroUser;
import com.aluraforohub.forohub.persistence.user.DatosRespuestaUser;
import com.aluraforohub.forohub.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    // Guardar un registro nuevo
    @PostMapping
    @PreAuthorize("hasAuthority('CREATE') or hasAuthority('REFACTOR')")
    @Transactional
    public ResponseEntity<DatosRespuestaUser> registrarUser(@RequestBody @Valid DatosRegistroUser datosRegistroUser, UriComponentsBuilder uriComponentsBuilder) {
        var passwordEncoder = new BCryptPasswordEncoder();
        //passwordEncoder.encode(users.getPassword()),
        DatosRegistroUser datosRegistroUser2 = new DatosRegistroUser(
                datosRegistroUser.username(), passwordEncoder.encode(datosRegistroUser.password()), // encriptamos la clave del usuario.
                datosRegistroUser.isEnable(), datosRegistroUser.accountNoExpired(),
                datosRegistroUser.accountNoLocked(), datosRegistroUser.credentialNoExpired(),
                datosRegistroUser.roles());
        UserEntity users = userRepository.save(new UserEntity(datosRegistroUser2));
        DatosRespuestaUser datosRespuestaUser = new DatosRespuestaUser(
                users.getId(), users.getUsername(), users.getPassword(),
                users.isEnable(), users.isAccountNoExpired(), users.isAccountNoLocked(),
                users.isCredentialNoExpired());
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(users.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaUser);
    }

//    public ResponseEntity<DatosRespuestaUser> registrarUser(@RequestBody @Valid DatosRegistroUser datosRegistroUser, UriComponentsBuilder uriComponentsBuilder) {
//        var passwordEncoder = new BCryptPasswordEncoder();
//        //passwordEncoder.encode(users.getPassword()),
//        DatosRegistroUser datosRegistroUser2 = new DatosRegistroUser(
//                datosRegistroUser.username(), passwordEncoder.encode(datosRegistroUser.password()), // encriptamos la clave del usuario.
//                datosRegistroUser.isEnable(), datosRegistroUser.accountNoExpired(),
//                datosRegistroUser.accountNoLocked(), datosRegistroUser.credentialNoExpired());
//
//        UserEntity users = userRepository.save(new UserEntity(datosRegistroUser2));
//        DatosRespuestaUser datosRespuestaUser = new DatosRespuestaUser(
//                users.getId(), users.getUsername(), users.getPassword(),
//                users.isEnable(), users.isAccountNoExpired(), users.isAccountNoLocked(),
//                users.isCredentialNoExpired());
//        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(users.getId()).toUri();
//        return ResponseEntity.created(url).body(datosRespuestaUser);
//    }

    @GetMapping("/get")
    //@PreAuthorize("hasAuthority('READ')")
    public String helloGet(){
        return "Hello World - GET";
    }

//    @PostMapping("/post")
//    //@PreAuthorize("hasAuthority('CREATE') or hasAuthority('READ')")
//    public String helloPost(){
//        return "Hello World - POST";
//    }

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

    // Nota: en las siguientes configuraciones los mappings que no tengan @PreAuthorize configurado no podran acceder pues en la anotacion superior esta denyAll para todos.
//    @GetMapping("/hello")
//    @PreAuthorize("permitAll")
//    public String hello(){
//        return "Hello World";
//    }
//
//    @GetMapping("/hellosecurity")
//    @PreAuthorize("hasAuthority('READ')")
//    public String helloSecurity(){
//        return "Hello World Security";
//    }
//
//    @GetMapping("/hellosecurity2")
//    @PreAuthorize("hasAuthority('CREATE')")
//    public String helloSecurity2(){
//        return "Hello World Security2";
//    }


}
