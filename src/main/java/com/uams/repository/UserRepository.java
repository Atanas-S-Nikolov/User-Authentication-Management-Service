package com.uams.repository;

import com.uams.model.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    @Query("select u from UserEntity u where u.username = :username")
    Optional<UserEntity> findByUsername(@Param(value = "username") String username);

    @Modifying
    @Query("update UserEntity u set u.isLoggedIn = :loginStatus where u.id = :id")
    void updateLoginStatus(@Param(value = "id") String id, @Param(value = "loginStatus") Boolean loginStatus);
}
