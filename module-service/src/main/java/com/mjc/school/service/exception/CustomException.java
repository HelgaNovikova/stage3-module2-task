package com.mjc.school.service.exception;

public abstract class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }

    public abstract String getCode();
}