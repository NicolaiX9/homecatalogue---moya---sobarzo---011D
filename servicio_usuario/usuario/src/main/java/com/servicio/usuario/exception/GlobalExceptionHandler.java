package com.servicio.usuario.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.servicio.usuario.config.ErrorResponse;


@RestControllerAdvice
public class GlobalExceptionHandler {

    //Manejar cuando no se halla un recurso (404) 
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse>
    handleRuntimeException(RuntimeException ex) {
        ErrorResponse error = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage(), System.currentTimeMillis());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    }
