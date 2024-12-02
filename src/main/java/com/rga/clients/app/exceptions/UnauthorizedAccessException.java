package com.rga.clients.app.exceptions;

public class UnauthorizedAccessException extends RuntimeException {

    public UnauthorizedAccessException() {
        super();
    }

    public UnauthorizedAccessException(String message) {
        super(message);
    }
}