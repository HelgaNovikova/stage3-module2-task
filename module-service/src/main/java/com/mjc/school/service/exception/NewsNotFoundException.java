package com.mjc.school.service.exception;

public class NewsNotFoundException extends CustomException {
    public NewsNotFoundException(String message) {
        super(message);
    }

    public String getCode() {
        return "000001";
    }
}
