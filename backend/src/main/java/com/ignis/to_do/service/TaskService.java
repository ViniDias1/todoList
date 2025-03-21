package com.ignis.to_do.service;

import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.stereotype.Service;

import com.ignis.to_do.dto.TaskDTO;
import com.ignis.to_do.exception.task_exception.TaskNotFoundException;
import com.ignis.to_do.model.Task;
import com.ignis.to_do.model.TaskList;
import com.ignis.to_do.repository.TaskRepository;
import com.ignis.to_do.validator.StatusValidator;

import jakarta.transaction.Transactional;

@Service
public class TaskService implements TaskReminder {

    private final TaskRepository taskRepository;
    private final TaskListService taskListService;

    private static final String TASK_NOT_FOUND = "Task com ID %s nao encontrado";

    public TaskService(TaskRepository taskRepository, TaskListService taskListService) {
        this.taskRepository = taskRepository;
        this.taskListService = taskListService;
    }

    public String createTask(TaskDTO taskDTO) {  

        StatusValidator taskStatus = new StatusValidator(taskDTO.getStatus());
        taskStatus.validateStatus(taskDTO.getStatus());

        if (taskStatus.validateStatus(taskDTO.getStatus())) {            
            TaskList taskList = taskListService.getList(taskDTO.getListId());        
            Task task = new Task(taskDTO.getTitle(), taskList,taskDTO.getStatus());      
            taskRepository.save(task);        
            return "Task criada com sucesso";
        }

        return "Status inválido";
    }

    public TaskDTO getTaskById(Long taskId) {  
        Task task = taskRepository.findById(taskId).orElseThrow(()
         -> new TaskNotFoundException(TASK_NOT_FOUND.formatted(taskId))); 
        return new TaskDTO(task.getId(), task.getTitle(), task.getStatus(), task.getList().getId()); 
    }

    public void verifyIfTaskExists(Long taskId) {
        taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(TASK_NOT_FOUND.formatted(taskId)));
    }

    public Iterable<TaskDTO> getAllTasks() {

        return taskRepository.findAll().stream().map(task -> new TaskDTO(task.getId(),
            task.getTitle(), task.getStatus(), task.getList().getId())).toList();
    }
    
    public void deleteTaskById(Long taskId) { 
        verifyIfTaskExists(taskId);       
        taskRepository.deleteById(taskId);     
    }

    @Transactional
    public TaskDTO updateTaskTitle(TaskDTO taskDTO) {

        verifyIfTaskExists(taskDTO.getId());
        String title = taskDTO.getTitle();
        Long taskListId = taskDTO.getId(); 
        taskRepository.updateTaskTitle(taskListId, title);
        Task task = taskRepository.findById(taskListId).orElseThrow(()
         -> new TaskNotFoundException(TASK_NOT_FOUND.formatted(taskListId))); 

        return new TaskDTO(taskListId, title, task.getStatus(),task.getList().getId());
    }
    
    @Override
    public String checkOverdueTasks(Long taskId) {
        
        verifyIfTaskExists(taskId);
        Task task = taskRepository.findById(taskId).orElseThrow(()
         -> new TaskNotFoundException(TASK_NOT_FOUND.formatted(taskId)));
        LocalDate today = LocalDate.now();
        LocalDate taskDueDate = task.getDueDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        if (taskDueDate.isBefore(today)) {
            sendTaskReminder();
            return "Lembrete de tarefa enviado.";
        } 
        
        return "A tarefa ainda não está atrasada.";
 
    }

    @Override
    public void sendTaskReminder() {
      // TODO document why this method is empty
    }
}
