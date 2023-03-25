package com.mjc.school.service.exception;

public class AuthorNameException extends CustomException {

    public AuthorNameException(String message) {
        super(message);
    }

    public String getCode() {
        return "000013";
    }
}