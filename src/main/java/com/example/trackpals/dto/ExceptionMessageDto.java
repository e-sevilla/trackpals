package com.example.trackpals.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Getter
@Setter
public class ExceptionMessageDto {

    private int status;
    private HttpStatus error;
    private String message;

    public ExceptionMessageDto(HttpStatus error, String message){
        this.status = error.value();
        this.error = error;
        this.message = message;
    }

}
