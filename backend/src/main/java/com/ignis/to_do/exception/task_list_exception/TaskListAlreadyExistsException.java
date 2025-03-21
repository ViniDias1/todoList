package com.ignis.to_do.exception.task_list_exception;

public class TaskListAlreadyExistsException extends RuntimeException {
    public TaskListAlreadyExistsException(String message) {
        super(message);
    }
}
