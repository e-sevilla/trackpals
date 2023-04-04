package com.example.trackpals.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) //codigo 404
public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException(String message){
        super(message);
    }

}
