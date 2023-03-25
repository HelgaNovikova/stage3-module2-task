package com.mjc.school.service.exception;

public class AuthorNotFoundException extends CustomException {

    public AuthorNotFoundException(String message) {
        super(message);
    }

    public String getCode() {
        return "000002";
    }
}