package com.ignis.to_do.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ignis.to_do.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Modifying
    @Query("UPDATE board b SET b.title = :title WHERE b.id = :id")
    void updateTitle(
        @Param("id") Long id,
        @Param("title") String title
        );

    
}