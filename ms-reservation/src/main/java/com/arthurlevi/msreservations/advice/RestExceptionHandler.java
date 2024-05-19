package com.arthurlevi.msreservations.advice;

import com.arthurlevi.msreservations.exceptions.UnavailableTimeException;
import com.arthurlevi.msreservations.exceptions.UserOrRoomNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UnavailableTimeException.class)
    private ResponseEntity<String> UnavailableTime(UnavailableTimeException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getLocalizedMessage());
    }

    @ExceptionHandler(UserOrRoomNotFoundException.class)
    private ResponseEntity<String> UserOrRoomNotFound(UserOrRoomNotFoundException exception){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getLocalizedMessage());
    }



}
