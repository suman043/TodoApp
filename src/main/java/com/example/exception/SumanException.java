package com.example.exception;

import org.springframework.http.HttpStatus;

public class SumanException extends Exception {

    private final HttpStatus status;

    public SumanException(String message, Throwable cause) {
        super(message, cause);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public SumanException(String message, HttpStatus status) {
        super((message == null || message.isEmpty()) ? "Internal Server Error" : message);
        this.status = (status != null) ? status : HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public HttpStatus getStatus() {
        return status;
    }
}