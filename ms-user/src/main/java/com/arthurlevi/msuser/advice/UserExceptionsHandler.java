package com.arthurlevi.msuser.advice;

import com.arthurlevi.msuser.exceptions.CreateUserException;
import com.arthurlevi.msuser.exceptions.UserPrincipalNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class UserExceptionsHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleInvalidateArgument(MethodArgumentNotValidException exception){
        Map<String,String> errorMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(error -> {errorMap.put(error.getField(),error.getDefaultMessage());
                });
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserPrincipalNotFoundException.class)
    public Map<String,String> handleBusinessException(UserPrincipalNotFoundException exception){

        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("errorMessage",exception.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CreateUserException.class)
    public Map<String,String> handleCreateUserException(CreateUserException exeption){

        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("errorMessage",exeption.getMessage());
        return errorMap;
    }

}
