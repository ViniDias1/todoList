package com.ignis.to_do.config;

import com.ignis.to_do.exception.GlobalException;
import com.ignis.to_do.exception.board_exception.BoardAlreadyExistsException;
import com.ignis.to_do.exception.board_exception.BoardNotFoundException;
import com.ignis.to_do.exception.category_exception.CategoryAlreadyExistsException;
import com.ignis.to_do.exception.category_exception.CategoryNotFoundException;
import com.ignis.to_do.exception.task_exception.TaskAlreadyExistsException;
import com.ignis.to_do.exception.task_exception.TaskNotFoundException;
import com.ignis.to_do.exception.task_list_exception.TaskListAlreadyExistsException;
import com.ignis.to_do.exception.task_list_exception.TaskListNotFoundException;
import com.ignis.to_do.exception.user_exception.UserAlreadyExistsException;
import com.ignis.to_do.exception.user_exception.UserNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(String error, String message, HttpStatus status) {
        Map<String, Object> response = Map.of(
                "timestamp", LocalDateTime.now(),
                "status", status.value(),
                "error", error,
                "message", message
        );
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<Object> handleGlobalException(GlobalException ex) {
        return buildResponseEntity("Erro Global", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        return buildResponseEntity("Erro interno no servidor", "Erro interno no servidor", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({
            UserNotFoundException.class, BoardNotFoundException.class, 
            TaskListNotFoundException.class, TaskNotFoundException.class, 
            CategoryNotFoundException.class
    })
    public ResponseEntity<Object> handleNotFoundExceptions(RuntimeException ex) {
        return buildResponseEntity("Recurso não encontrado", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            UserAlreadyExistsException.class, BoardAlreadyExistsException.class, 
            TaskListAlreadyExistsException.class, TaskAlreadyExistsException.class, 
            CategoryAlreadyExistsException.class
    })
    public ResponseEntity<Object> handleAlreadyExistsExceptions(RuntimeException ex) {
        return buildResponseEntity("Conflito de recurso", ex.getMessage(), HttpStatus.CONFLICT);
    }
}
