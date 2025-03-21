package com.ignis.to_do.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import com.ignis.to_do.dto.TaskListDTO;
import com.ignis.to_do.exception.task_list_exception.TaskListAlreadyExistsException;
import com.ignis.to_do.exception.task_list_exception.TaskListNotFoundException;
import com.ignis.to_do.model.Board;
import com.ignis.to_do.model.TaskList;
import com.ignis.to_do.repository.TaskListRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskListService {

    private final TaskListRepository taskListRepository;
    private final BoardService boardService;

    private static final String TASK_LIST_NOT_FOUND = "TaskList com ID %s nao encontrado";

    public TaskListService(TaskListRepository taskListRepository, BoardService boardService) {
        this.taskListRepository = taskListRepository;
        this.boardService = boardService;
    }
    
    public TaskListDTO createTaskList(TaskListDTO taskListDTO) {

        Optional<TaskList> existingList = taskListRepository.findByName(taskListDTO.getName());
        if (existingList.isPresent()) {
            throw new TaskListAlreadyExistsException("TaskList com nome '" + taskListDTO.getName() + "' já existe.");
        }

        Board board = boardService.getBoard(taskListDTO.getBoardId());
        TaskList taskList = new TaskList(taskListDTO.getName(), board);
        taskListRepository.save(taskList);
        return new TaskListDTO(taskList.getId(), taskList.getName(), 
            taskList.getBoard().getId());
    }

    public TaskListDTO getTaskListById(Long taskLitsId) {
        TaskList taskList = taskListRepository.findById(taskLitsId).orElseThrow(() -> 
        new TaskListNotFoundException(TASK_LIST_NOT_FOUND.formatted(taskLitsId)));

        return new TaskListDTO(taskList.getId(), taskList.getName(), 
            taskList.getBoard().getId());
    }

    public void verifyIfTaskListExists(Long taskListId) {
        taskListRepository.findById(taskListId).orElseThrow(() -> 
        new TaskListNotFoundException(TASK_LIST_NOT_FOUND.formatted(taskListId)));
    }

    public Iterable<TaskListDTO> getAllTaskLists() {
        return taskListRepository.findAll().stream().map(taskList -> new TaskListDTO(
            taskList.getId(), taskList.getName(), taskList.getBoard().getId())).toList();
    }

    public TaskList getList(Long taskListId) {
        return taskListRepository.findById(taskListId).orElseThrow(() -> 
        new TaskListNotFoundException(TASK_LIST_NOT_FOUND.formatted(taskListId)));        
    }

    public void deleteTaskList(TaskList taskList) {     
        verifyIfTaskListExists(taskList.getId());   
        taskListRepository.delete(taskList);        
    }

    public void deleteTaskListById(Long taskListId) {
        verifyIfTaskListExists(taskListId);   
        taskListRepository.deleteById(taskListId);
    }

    public TaskListDTO updateTaskListTitle(TaskListDTO taskListDTO) {

        TaskList taskList = taskListRepository.findById(taskListDTO.getId()).orElseThrow(() -> 
        new TaskListNotFoundException(TASK_LIST_NOT_FOUND.formatted(taskListDTO.getId())));
        taskList.setName(taskListDTO.getName());
        taskListRepository.save(taskList);
        return new TaskListDTO(taskList.getId(), taskList.getName(), 
                                taskList.getBoard().getId());
    }

    @Transactional
    public TaskListDTO updateBoardId(TaskListDTO taskListDTO) {

        verifyIfTaskListExists(taskListDTO.getId());
        Long taskListId = taskListDTO.getId();
        taskListRepository.updateBoardId(taskListDTO.getId(), taskListDTO.getBoardId());
        TaskList taskList = taskListRepository.findById(taskListId).orElseThrow(() -> 
        new TaskListNotFoundException(TASK_LIST_NOT_FOUND.formatted(taskListId)));
        return new TaskListDTO(taskListId, taskList.getName(),
                                taskListDTO.getBoardId());

    }

}
