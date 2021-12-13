package com.example.projectone.excpetion;

public class ApplicationException extends RuntimeException {

    private String message;

    public ApplicationException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

