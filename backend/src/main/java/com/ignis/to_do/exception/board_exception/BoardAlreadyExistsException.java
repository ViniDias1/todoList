package com.ignis.to_do.exception.board_exception;

public class BoardAlreadyExistsException extends RuntimeException {
    public BoardAlreadyExistsException(String message) {
        super(message);
    }
}
