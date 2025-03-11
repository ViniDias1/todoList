package com.ignis.to_do.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ignis.to_do.dto.TaskListDTO;
import com.ignis.to_do.model.Board;
import com.ignis.to_do.model.TaskList;
import com.ignis.to_do.repository.TaskListRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskListService {
    @Autowired
    private TaskListRepository taskListRepository;
    @Autowired
    private BoardService boardService;
    
    public TaskListDTO createTaskList(String title, Long boardId) {  

        Board board = boardService.getBoard(boardId);
        TaskList taskList = new TaskList(title, board);
        taskListRepository.save(taskList);
        return new TaskListDTO(taskList.getId(), taskList.getName(), 
            taskList.getBoard().getId());
    }

    // imp createTaskList(Board board) {
        
    // }

    public void deleteTaskList(TaskList taskList) {        
        taskListRepository.delete(taskList);        
    }

    public TaskListDTO getTaskList(Long id) {
        TaskList taskList = taskListRepository.findById(id).get();
        return new TaskListDTO(taskList.getId(), taskList.getName(), 
            taskList.getBoard().getId());
    }

    public Iterable<TaskListDTO> getAllTaskLists() {
        return taskListRepository.findAll().stream().map(taskList -> new TaskListDTO(
            taskList.getId(), taskList.getName(), taskList.getBoard().getId())).toList();
    }

    public TaskList getList(Long id) {
        return taskListRepository.findById(id).get();        
    }

    public void deleteTaskList(Long id) {
        taskListRepository.deleteById(id);
    }

    public TaskListDTO updateTaskListTitle(Long id, String title) {
        TaskList taskList = taskListRepository.findById(id).get();
        taskList.setName(title);
        taskListRepository.save(taskList);
        return new TaskListDTO(taskList.getId(), taskList.getName(), 
        taskList.getBoard().getId());
    }

    @Transactional
    public TaskListDTO updateBoardId(Long id, Long boardId) {
        taskListRepository.updateBoardId(id, boardId);
        return new TaskListDTO(id, taskListRepository.findById(id).get().getName(), boardId);
    }

}
