package com.ecommenrce.project.exception;

import com.ecommenrce.project.dto.APIResponse;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice

public class MyGlobalExceptionHandler {
     // @ExceptionHandler(Exception.class)
    @ExceptionHandler(Exception.class)
    public ResponseEntity< Map<String,String>> myMethodArgumentNotValidException(MethodArgumentNotValidException e){
        Map<String,String> response = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(err ->{
            String fieldsName =((FieldError)err).getField();
            String message = err.getDefaultMessage();
            response.put(fieldsName,message);
        });
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse> myResourceNotFoundException(ResourceNotFoundException e){
        String message = e.getMessage();
        APIResponse apiResponse = new APIResponse(message,false);
     return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);

    }@ExceptionHandler(ApiException.class)
    public ResponseEntity<APIResponse> myAPIException(ApiException e){
     String message = e.getMessage();
        APIResponse apiResponse = new APIResponse(message,false);

        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }
}
