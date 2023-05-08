package com.example.trackpals.exception;

import com.example.trackpals.dto.ExceptionMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionControl {

    //codigo 404
    //Si un documento a buscar no existe
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ExceptionMessageDto throwResourceNotFoundException(ResourceNotFoundException e){
        return new ExceptionMessageDto(HttpStatus.NOT_FOUND, e.getMessage() + " no encontrado");
    }

    //codigo 400
    //Si el valor de un atributo unico ya existe
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AttributeException.class)
    public ExceptionMessageDto throwAttributeException(AttributeException e){
        return new ExceptionMessageDto(HttpStatus.BAD_REQUEST, e.getMessage() + " ya existe");
    }

    //codigo 406
    //Si el valor de un atributo no es valido
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionMessageDto validationException(MethodArgumentNotValidException e){
        String message = "";
        List<ObjectError> errores = e.getBindingResult().getAllErrors();
        for (ObjectError error : errores){
            message += (message.length() == 0) ? "" : "\n";
            message += error.getDefaultMessage();
        }
        return new ExceptionMessageDto(HttpStatus.NOT_ACCEPTABLE, message);
    }

    //codigo 500
    //Si ocurre cualquier otra excepcion
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ExceptionMessageDto generalException(Exception e){
        return new ExceptionMessageDto(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

}
