package com.example.awskafkajwt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RegisterRequestParamNullException extends ResponseStatusException {

    public RegisterRequestParamNullException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
