package com.mjc.school.service.exception;

public class TitleLengthException extends CustomException {

    public TitleLengthException(String message) {
        super(message);
    }

    public String getCode() {
        return "000012";
    }
}
