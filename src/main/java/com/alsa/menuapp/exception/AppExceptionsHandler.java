package com.alsa.menuapp.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.alsa.menuapp.model.ErrorMessage;

@ControllerAdvice
public class AppExceptionsHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest req){
        String errorMessageDescription = ex.getLocalizedMessage();

        if(errorMessageDescription == null) errorMessageDescription = ex.toString();

        ErrorMessage errorMessage = new ErrorMessage(errorMessageDescription, new Date());

        return new ResponseEntity<>(
            errorMessage, 
            new HttpHeaders(), 
            HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(value = {ResourceAlreadyExistsException.class})
    public ResponseEntity<Object> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex, WebRequest req){
        String errorMessageDescription = ex.getLocalizedMessage();

        if(errorMessageDescription == null) errorMessageDescription = ex.toString();

        ErrorMessage errorMessage = new ErrorMessage(errorMessageDescription, new Date());

        return new ResponseEntity<>(
            errorMessage, 
            new HttpHeaders(), 
            HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceAlreadyExistsException(ResourceNotFoundException ex, WebRequest req){
        String errorMessageDescription = ex.getLocalizedMessage();

        if(errorMessageDescription == null) errorMessageDescription = ex.toString();

        ErrorMessage errorMessage = new ErrorMessage(errorMessageDescription, new Date());

        return new ResponseEntity<>(
            errorMessage, 
            new HttpHeaders(), 
            HttpStatus.NOT_FOUND
        );
    }
}