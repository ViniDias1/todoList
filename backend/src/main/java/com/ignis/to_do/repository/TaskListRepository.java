package com.ignis.to_do.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ignis.to_do.model.TaskList;

public interface TaskListRepository extends JpaRepository<TaskList, Long>{
    @Modifying
    @Query("UPDATE task_list tl SET tl.board.id = :boardId WHERE tl.id = :id")
    void updateBoardId(
        @Param("id") Long id,
        @Param("boardId") Long boardId
        );
}