package com.ignis.to_do.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ignis.to_do.dto.TaskDTO;
import com.ignis.to_do.model.Task;
import com.ignis.to_do.model.TaskList;
import com.ignis.to_do.repository.TaskRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskListService taskListService;

    public TaskDTO createTask(String title, Long taskListId) {
        TaskList taskList = taskListService.getList(taskListId);
        Task task = new Task(title, taskList);
        taskRepository.save(task);
        return new TaskDTO(task.getId(), title, taskListId);
    }
    public Task createTask(Task task) {        
        return taskRepository.save(task);
    }

    public TaskDTO getTask(Long id) {  
        Task task = taskRepository.findById(id).get();      
        return new TaskDTO(task.getId(), task.getTitle(), task.getList().getId()); 
    }

    public Iterable<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream().map(task -> new TaskDTO(task.getId(),
            task.getTitle(), task.getList().getId())).toList();
    }
    
    public void deleteTask(Long id) {        
        taskRepository.deleteById(id);     
    }
    @Transactional
    public TaskDTO updateTaskTitle(Long id, String title) {
        taskRepository.updateTaskTitle(id, title);        
        return new TaskDTO(id, title, taskRepository.findById(id).get().getList().getId());
    }
    
}
