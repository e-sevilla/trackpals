package com.example.trackpals.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) //codigo 400
public class AttributeException extends Exception {

    public AttributeException(String message){
        super(message);
    }

}
