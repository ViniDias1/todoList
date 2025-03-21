package com.ignis.to_do.exception.board_exception;

public class BoardNotFoundException extends RuntimeException {
    public BoardNotFoundException(String message) {
        super(message);
    }
}
