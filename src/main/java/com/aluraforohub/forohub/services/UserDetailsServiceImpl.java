package com.aluraforohub.forohub.services;

import com.aluraforohub.forohub.persistence.Authentication.UserEntity;
import com.aluraforohub.forohub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //        //       // buscamos el usuario por
        UserEntity userEntity = userRepository.findUserEntityByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario" + username + " no existe."));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userEntity.getRoles()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));

        userEntity.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));

        return new User(userEntity.getUsername(), userEntity.getPassword(),
                userEntity.isEnable(), userEntity.isAccountNoExpired(),
                userEntity.isCredentialNoExpired(), userEntity.isAccountNoLocked(),
                authorityList);

//        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
//
//        // Agregamos los roles y los asignamos a la autorizacion
//        userEntity.getRoles()
//                .forEach(role ->authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));
//        // Agregamos los permisos
//        userEntity.getRoles().stream()
//                .flatMap(role -> role.getPermission().stream())
//                        .forEach((permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName()))));
//
//        return new User(userEntity.getUsername(),
//                        userEntity.getUsername(),
//                        userEntity.isEnable(),
//                        userEntity.isAccountNoExpired(),
//                        userEntity.isCredentialNoExpired(),
//                        userEntity.isAccountNoLocked(),
//                        authorityList);

    }

    //    @Override
//    public UserDetailsService loadUserByUsername(String username) throws UsernameNotFoundException {
//       // buscamos el usuario por
//
//        UserEntity userEntity = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("El usuario" + username + " no existe."));
//
//        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
//
//        // Agregamos los roles y los asignamos a la autorizacion
//        userEntity.getRoles()
//                .forEach(role ->authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));
//        // Agregamos los permisos
//        userEntity.getRoles().stream()
//                .flatMap(role -> role.getPermissions().stream())
//                        .forEach((permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName()))));
//
//
//        return new User(userEntity.getUsername(),
//                        userEntity.getUsername(),
//                        userEntity.isEnable(),
//                        userEntity.isAccountNoExpired(),
//                        userEntity.isCredentialNoExpired(),
//                        userEntity.isAccountNoLocked(),
//                        authorityList);
//    }

}
