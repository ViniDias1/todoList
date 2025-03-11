package com.ignis.to_do.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "task")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;            
    private String title;
    private String description;
    private TaskStatus status;
    @ManyToOne
    @JoinColumn(name = "list_id")
    private TaskList list;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private Date dueDate;

    public Task(String title, TaskList list) {
        this.title = title;
        this.list = list;
    }

    public Task createTask(Task task) {  
        Task newTask = new Task();
        newTask.setTitle(task.getTitle());
        newTask.setDescription(task.getDescription());
        newTask.setStatus(task.getStatus());
        newTask.setList(task.getList());        
        newTask.setCategory(task.getCategory());
        newTask.setDueDate(task.getDueDate());
        return newTask;      
        
    } 

    public void deleteTask(Task task) {      

        this.list.removeTask(task);
    }

    public void markAsComplete() {
        // TO DO
    } 
    
    public void assignCategory(Category category) {
        //this.category = category;
        this.setCategory(category);
    }

    public void isOverdue(){
        // TO DO
    }
}