package com.ignis.to_do.exception.task_exception;

public class TaskAlreadyExistsException extends RuntimeException {
    public TaskAlreadyExistsException(String message) {
        super(message);
    }
}
