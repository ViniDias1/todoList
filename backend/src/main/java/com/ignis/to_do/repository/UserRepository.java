package com.ignis.to_do.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ignis.to_do.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByNameAndEmail(String name, String email);
    Optional<User> findByNameAndEmail(String name, String email);

    @Modifying
    @Query("UPDATE users u SET u.name = :name, u.email = :email WHERE u.id = :id")
    void updateUser(
        @Param("id") long id,
        @Param("name") String name,
        @Param("email") String email
        );
}
