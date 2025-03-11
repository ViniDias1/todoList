package com.ignis.to_do.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ignis.to_do.model.Task;


public interface TaskRepository extends JpaRepository<Task, Long> {
    @Modifying
    @Query("UPDATE task tk SET tk.title = :title WHERE tk.id = :id")
    void updateTaskTitle(@Param("id") Long id, @Param("title") String title);
}