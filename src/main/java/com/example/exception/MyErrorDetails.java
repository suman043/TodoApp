package com.example.exception;

public class MyErrorDetails {

    private int statusCode;
    private String message;
    private String details;

    // No-args constructor
    public MyErrorDetails() {
    }

    // All-args constructor
    public MyErrorDetails(int statusCode, String message, String details) {
        this.statusCode = statusCode;
        this.message = message;
        this.details = details;
    }

    // Getters
    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    // Setters
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    // toString method
    @Override
    public String toString() {
        return "MyErrorDetails{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
