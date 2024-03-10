package com.ebenezer.audit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({GenericNotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundException(GenericNotFoundException ex){
        return createResponse(ex, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Object> createResponse(Exception ex, HttpStatus status){
        Map<String, Object> response = new HashMap<>();
        HttpStatus httpStatus = status != null ? status : HttpStatus.INTERNAL_SERVER_ERROR;
        String message = ex.getMessage() != null ? ex.getMessage() : "Server Error";
        response.put("status", httpStatus);
        response.put("message", message);
        response.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(httpStatus.value()).body(response);
    }
}
