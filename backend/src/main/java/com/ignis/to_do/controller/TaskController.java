package com.ignis.to_do.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ignis.to_do.dto.TaskDTO;
import com.ignis.to_do.service.TaskService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/task")
// @AllArgsConstructor Analisar a necesseidade para evitar o @Autowired
// @NoArgsConstructor   
@Tag(name = "Task Controller", description = "Gerenciamento de tarefas")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping("/createTask/{titulo}/{taskListId}")
    public TaskDTO createTask(
        @PathVariable String titulo,
        @PathVariable Long taskListId) {

        return taskService.createTask(titulo, taskListId);
    }

    @GetMapping("/getTask/{id}")
    public TaskDTO getTask(@PathVariable Long id) {

        return taskService.getTask(id);
    }   

    @GetMapping("/getAllTasks")
    public Iterable<TaskDTO> getAllTasks() {

        return taskService.getAllTasks();
    }   

    @DeleteMapping("/deleteTask/{id}")
    public void deleteTask(@PathVariable Long id) {
        
        taskService.deleteTask(id); 
    }

    @PutMapping("/updateTask/{id}/{title}")
    public TaskDTO updateTaskTitle(
        @PathVariable Long id,
        @PathVariable String title) {
            
        return taskService.updateTaskTitle(id, title);
    }
}   
