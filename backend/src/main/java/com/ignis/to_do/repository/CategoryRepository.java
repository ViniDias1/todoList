package com.ignis.to_do.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ignis.to_do.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

    Optional<Category> findByName(String name);

    @Modifying
    @Query("UPDATE category c SET c.name = :name WHERE c.id = :id")
    void updateCategoryName(
        @Param("id") Long id,
        @Param("name") String name
        );

    
}