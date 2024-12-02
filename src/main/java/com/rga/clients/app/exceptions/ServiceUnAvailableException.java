package com.rga.clients.app.exceptions;

public class ServiceUnAvailableException extends RuntimeException {

    public ServiceUnAvailableException() {
        super();
    }

    public ServiceUnAvailableException(String message) {
        super(message);
    }
}
