package com.ignis.to_do.exception.task_list_exception;

public class TaskListNotFoundException extends RuntimeException {
    public TaskListNotFoundException(String message) {
        super(message);
    }
}
