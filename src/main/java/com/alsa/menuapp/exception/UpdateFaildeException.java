package com.alsa.menuapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UpdateFaildeException extends RuntimeException {
    
    public UpdateFaildeException(String message){
        super(message);
    }
}
