package org.example.testtask.controllers;

import org.example.testtask.dto.ErrorResponse;
import org.example.testtask.exceptions.CatNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {
            CatNotFoundException.class
    })
    public ResponseEntity<ErrorResponse> serverError(RuntimeException e, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        return ResponseEntity.status(status).body(new ErrorResponse(status.value(), e.getMessage()));
    }

    @ExceptionHandler(value = {
            MethodArgumentNotValidException.class
    })
    public ResponseEntity<ErrorResponse> badRequest(MethodArgumentNotValidException e, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status).body(new ErrorResponse(status.value(), e.getMessage()));
    }
}
