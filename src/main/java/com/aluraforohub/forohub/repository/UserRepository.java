package com.aluraforohub.forohub.repository;

import com.aluraforohub.forohub.persistence.Authentication.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // primera forma de hacerlo
    Optional<UserEntity> findUserEntityByUsername(String username);
    // segunda forma de hacerlo
    @Query("SELECT u FROM UserEntity u WHERE u.username = :username")
    Optional<UserEntity> buscarUser(String username);

}
