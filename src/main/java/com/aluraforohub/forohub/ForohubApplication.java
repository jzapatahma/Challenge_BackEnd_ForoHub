package com.aluraforohub.forohub;

import com.aluraforohub.forohub.persistence.Authentication.PermissionEntity;
import com.aluraforohub.forohub.persistence.Authentication.RoleEntity;
import com.aluraforohub.forohub.persistence.Authentication.RoleEnum;
import com.aluraforohub.forohub.persistence.Authentication.UserEntity;
import com.aluraforohub.forohub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class ForohubApplication {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(ForohubApplication.class, args);
	}

	@Bean
		// antes de iniciar la aplicacion se ejecutara el siguiente bloque
	CommandLineRunner init(UserRepository userRepository){
		// Creamos una lista inicial de usuario, roles y permisos.
		// Sin embargo desde los EndPoint tambien se pueden manipular(CRUD) en las tablas de autenticacion,
		// realizamos una misma clave para todos en esta carga inicial.
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String clave = bCryptPasswordEncoder.encode("1234");
		return args -> {
			if(userRepository.findAll().isEmpty())
			{
				//Crearemos LOS PERMISOS
				PermissionEntity createPermission = PermissionEntity.builder()
						.name("CREATE")
						.build();
				PermissionEntity readPermission = PermissionEntity.builder()
						.name("READ")
						.build();
				PermissionEntity updatePermission = PermissionEntity.builder()
						.name("UPDATE")
						.build();
				PermissionEntity deletePermission = PermissionEntity.builder()
						.name("DELETE")
						.build();
				PermissionEntity refactorPermission = PermissionEntity.builder()
						.name("REFACTOR")
						.build();
				//Creamos LOS ROLES
				RoleEntity roleAdmin = RoleEntity.builder()
						.roleEnum(RoleEnum.ADMIN)
						.permissions(Set.of(createPermission, readPermission, updatePermission, deletePermission))
						.build();
				RoleEntity roleUser = RoleEntity.builder()
						.roleEnum(RoleEnum.USER)
						.permissions(Set.of(createPermission, readPermission))
						.build();
				RoleEntity roleInvited = RoleEntity.builder()
						.roleEnum(RoleEnum.INVITED)
						.permissions(Set.of(readPermission))
						.build();
				RoleEntity roleDeveloper = RoleEntity.builder()
						.roleEnum(RoleEnum.DEVELOPER)
						.permissions(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
						.build();
				//Creamos los usuarios
				UserEntity userSantiago = UserEntity.builder()
						.username("santiago")
						.password(clave)
						.isEnable(true)
						.accountNoExpired(true)
						.accountNoLocked(true)
						.credentialNoExpired(true)
						.roles(Set.of(roleAdmin))
						.build();
				UserEntity userDaniel = UserEntity.builder()
						.username("daniel")
						.password(clave)
						.isEnable(true)
						.accountNoExpired(true)
						.accountNoLocked(true)
						.credentialNoExpired(true)
						.roles(Set.of(roleUser))
						.build();
				UserEntity userAndrea = UserEntity.builder()
						.username("andrea")
						.password(clave)
						.isEnable(true)
						.accountNoExpired(true)
						.accountNoLocked(true)
						.credentialNoExpired(true)
						.roles(Set.of(roleInvited))
						.build();
				UserEntity userAnyi = UserEntity.builder()
						.username("anyi")
						.password(clave)
						.isEnable(true)
						.accountNoExpired(true)
						.accountNoLocked(true)
						.credentialNoExpired(true)
						.roles(Set.of(roleDeveloper))
						.build();
				//
				userRepository.saveAll(List.of(userSantiago, userDaniel, userAndrea, userAnyi));
			} else {
				System.out.println("\nLa carga de de usuario, roles y permisos ya fue realizada");
			}
		};
	}


}
