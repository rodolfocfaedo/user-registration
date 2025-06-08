package com.rodolfocf.user_registration.controller;

import com.rodolfocf.user_registration.infrastructure.exception.EmailAlreadyRegisteredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handleEmailAlreadyRegistered(EmailAlreadyRegisteredException emailAlreadyRegisteredException){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: " + emailAlreadyRegisteredException.getMessage());
    }
}
